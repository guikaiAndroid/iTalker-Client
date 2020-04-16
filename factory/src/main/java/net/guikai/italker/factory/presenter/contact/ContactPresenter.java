package net.guikai.italker.factory.presenter.contact;

import android.support.v7.util.DiffUtil;

import net.guikai.italker.common.widget.recycler.BaseRecyclerAdapter;
import net.guikai.italker.factory.data.user.ContactDataSource;
import net.guikai.italker.factory.data.user.ContactRepository;
import net.guikai.italker.factory.data.DataSource;
import net.guikai.italker.factory.data.helper.UserHelper;
import net.guikai.italker.factory.model.db.User;

import net.guikai.italker.factory.presenter.BaseSourcePresenter;
import net.guikai.italker.factory.utils.DiffUiDataCallback;

import java.util.List;

/**
 * Description: 联系人的Presenter实现
 * Crete by Anding on 2020-03-02
 */
public class ContactPresenter extends BaseSourcePresenter<User, User, ContactDataSource, ContactContract.View>
        implements ContactContract.Presenter, DataSource.SuccessCallback<List<User>> {

    public ContactPresenter(ContactContract.View view) {
        super(new ContactRepository(), view);
    }

    @Override
    public void start() {
        super.start();

        // 加载网络数据
        UserHelper.refreshContacts();
    }

    // 运行到这里的时候是子线程
    @Override
    public void onDataLoaded(List<User> users) {
        // 无论怎么操作，数据变更，最终都会通知到这里来
        final ContactContract.View view = getView();
        if (view == null)
            return;

        BaseRecyclerAdapter<User> adapter = view.getRecyclerAdapter();
        List<User> old = adapter.getItems();

        // 进行数据对比
        DiffUtil.Callback callback = new DiffUiDataCallback<>(old, users);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        // 调用基类方法进行界面刷新
        refreshData(result, users);
    }
}
