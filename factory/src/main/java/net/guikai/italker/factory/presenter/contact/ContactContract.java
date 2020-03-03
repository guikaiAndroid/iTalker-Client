package net.guikai.italker.factory.presenter.contact;

import net.guikai.italker.common.presenter.BaseContract;
import net.guikai.italker.factory.model.db.User;

/**
 * Description:
 * Crete by Anding on 2020-03-02
 */
public interface ContactContract {

    // 什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter {

    }

    // 都在基类完成了
    interface View extends BaseContract.RecyclerView<Presenter, User> {

    }
}
