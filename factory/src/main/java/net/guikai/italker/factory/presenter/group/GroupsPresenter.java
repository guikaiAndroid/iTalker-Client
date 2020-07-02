package net.guikai.italker.factory.presenter.group;

import android.support.v7.util.DiffUtil;

import net.guikai.italker.factory.data.group.GroupsDataSource;
import net.guikai.italker.factory.data.group.GroupsRepository;
import net.guikai.italker.factory.data.helper.GroupHelper;
import net.guikai.italker.factory.model.db.Group;
import net.guikai.italker.factory.presenter.BaseSourcePresenter;
import net.guikai.italker.factory.utils.DiffUiDataCallback;

import java.util.List;

/**
 * Description: 我的群组Presenter
 * Crete by Anding on 2020/7/2
 */
public class GroupsPresenter extends BaseSourcePresenter<Group, Group,
        GroupsDataSource, GroupsContract.View> implements GroupsContract.Presenter {

    public GroupsPresenter(GroupsContract.View view) {
        super(new GroupsRepository(), view);
    }

    @Override
    public void start() {
        super.start();

        // 加载网络数据, 以后可以优化到下拉刷新中
        // 只有用户下拉进行网络请求刷新
        GroupHelper.refreshGroups();
    }


    @Override
    public void onDataLoaded(List<Group> groups) {
        final GroupsContract.View view = getView();
        if (view == null)
            return;

        // 对比差异
        List<Group> old = view.getRecyclerAdapter().getItems();
        DiffUiDataCallback<Group> callback = new DiffUiDataCallback<>(old, groups);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        // 界面刷新
        refreshData(result, groups);
    }
}
