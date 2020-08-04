package net.guikai.italker.factory.presenter.group;

import net.guikai.italker.factory.model.db.view.MemberUserModel;
import net.guikai.italker.factory.presenter.BaseContract;

/**
 * Description: 群成员的契约
 * Crete by Anding on 2020/7/20
 */
public interface GroupMembersContract {
    interface Presenter extends BaseContract.Presenter {
        // 具有一个刷新的方法
        void refresh();
    }

    // 界面
    interface View extends BaseContract.RecyclerView<Presenter, MemberUserModel> {
        // 获取群的ID
        String getGroupId();
    }
}
