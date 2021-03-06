package net.guikai.italker.factory.data.user;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.guikai.italker.factory.data.BaseDbRepository;
import net.guikai.italker.factory.data.DataSource;
import net.guikai.italker.factory.model.db.User;
import net.guikai.italker.factory.model.db.User_Table;
import net.guikai.italker.factory.persistence.Account;

import java.util.List;

/**
 * Description: 联系人仓库
 * Crete by Anding on 2020-04-16
 */
public class ContactRepository extends BaseDbRepository<User> implements ContactDataSource {

    @Override
    public void load(DataSource.SuccessCallback<List<User>> callback) {
        super.load(callback);

        // 加载本地数据库数据
        SQLite.select()
                .from(User.class)
                .where(User_Table.isFollow.eq(true))
                .and(User_Table.id.notEq(Account.getUserId()))
                .orderBy(User_Table.name, true)
                .limit(100)
                .async()
                .queryListResultCallback(this)
                .execute();
    }

    @Override
    protected boolean isRequired(User user) {
        return user.isFollow() && !user.getId().equals(Account.getUserId());
    }
}
