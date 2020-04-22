package net.guikai.italker.factory.presenter.message;

import net.guikai.italker.factory.model.db.Session;
import net.guikai.italker.factory.presenter.BaseContract;

/**
 * Description:
 * Crete by Anding on 2020-04-22
 */
public interface SessionContract extends BaseContract {
    // 什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter {

    }

    // 都在基类完成了
    interface View extends BaseContract.RecyclerView<Presenter, Session> {

    }
}
