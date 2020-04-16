package net.guikai.italker.factory.presenter.contact;

import net.guikai.italker.factory.presenter.BaseContract;
import net.guikai.italker.factory.model.card.UserCard;

/**
 * Description: 关注联系人契约定义
 * Crete by Anding on 2020-03-02
 */
public interface FollowContract {
    // 任务调度者
    interface Presenter extends BaseContract.Presenter {
        // 关注一个人
        void follow(String id);
    }

    interface View extends BaseContract.View<Presenter> {
        // 成功的情况下返回一个用户的信息
        void onFollowSucceed(UserCard userCard);
    }

}
