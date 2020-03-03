package net.guikai.italker.factory.presenter.contact;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import net.guikai.italker.common.presenter.BasePresenter;
import net.guikai.italker.factory.data.DataSource;
import net.guikai.italker.factory.data.helper.UserHelper;
import net.guikai.italker.factory.model.card.UserCard;
import net.guikai.italker.factory.model.db.AppDatabase;
import net.guikai.italker.factory.model.db.User;
import net.guikai.italker.factory.model.db.User_Table;
import net.guikai.italker.factory.persistence.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Crete by Anding on 2020-03-02
 */
public class ContactPresenter extends BasePresenter<ContactContract.View>
        implements ContactContract.Presenter {
    public ContactPresenter(ContactContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        super.start();

        //  加载本地数据
        SQLite.select()
                .from(User.class)
                .where(User_Table.isFollow.eq(true))
                .and(User_Table.id.notEq(Account.getUserId()))
                .orderBy(User_Table.name,true)
                .limit(100)
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<User>() {
                    @Override
                    public void onListQueryResult(QueryTransaction transaction,
                                                  @NonNull List<User> tResult) {
                        getView().getRecyclerAdapter().replace(tResult);
                        getView().onAdapterDataChanged();
                    }
                })
                .execute();

        UserHelper.refreshContacts(new DataSource.CallBack<List<UserCard>>() {
            @Override
            public void onDataNotAvailable(int strRes) {

            }

            @Override
            public void onDataLoaded(List<UserCard> userCards) {

                final List<User> users = new ArrayList<>();
                for (UserCard userCard : userCards) {
                    users.add(userCard.build());
                }

                // 保存到数据库
                DatabaseDefinition databaseDefinition = FlowManager.getDatabase(AppDatabase.class);
                databaseDefinition.beginTransactionAsync(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        FlowManager.getModelAdapter(User.class)
                                .saveAll(users);

                    }
                }).build().execute();

                // 网络数据是最新的 我们需要直接刷新到界面
                getView().getRecyclerAdapter().replace(users);
                getView().onAdapterDataChanged();
            }
        });

        // TODO 问题
        // 1.关注后虽然存储数据库，但是没有刷新联系人页面
        // 2.如果刷新数据库，或者从网络刷新，最终刷新的时候是全局刷新
        // 3.本地刷新和网络刷新，再添加到界面的时候会有可能冲突 导致数据显示异常
    }
}
