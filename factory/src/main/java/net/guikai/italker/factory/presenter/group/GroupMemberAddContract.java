package net.guikai.italker.factory.presenter.group;

import net.guikai.italker.factory.presenter.BaseContract;

/**
 * Description: 群成员添加的契约
 * Crete by Anding on 2020/7/20
 */
public interface GroupMemberAddContract {

    interface Presenter extends BaseContract.Presenter {
        // 提交成员
        void submit();

        // 更改一个Model的选中状态
        void changeSelect(GroupCreateContract.ViewModel model, boolean isSelected);
    }

    // 界面
    interface View extends BaseContract.RecyclerView<Presenter, GroupCreateContract.ViewModel> {
        // 添加群成员成功
        void onAddedSucceed();

        // 群成员为空直接dismiss
        void dismissDialog();

        // 获取群的Id
        String getGroupId();
    }
}
