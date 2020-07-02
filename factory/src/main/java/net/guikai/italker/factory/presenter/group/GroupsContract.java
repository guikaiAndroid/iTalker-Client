package net.guikai.italker.factory.presenter.group;

import net.guikai.italker.factory.model.db.Group;
import net.guikai.italker.factory.presenter.BaseContract;

/**
 * Description: 我的群列表契约
 * Crete by Anding on 2020/7/2
 */
public interface GroupsContract {
    // 什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter {

    }

    // 都在基类完成了
    interface View extends BaseContract.RecyclerView<Presenter, Group> {

    }
}
