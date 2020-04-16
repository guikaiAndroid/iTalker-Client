package net.guikai.italker.factory.presenter.contact;

import net.guikai.italker.factory.presenter.BaseContract;
import net.guikai.italker.factory.model.db.User;

/**
 * Description: 个人信息逻辑契约
 * Crete by Anding on 2020-03-04
 */
public interface PersonalContract {
    interface Presenter extends BaseContract.Presenter {
        // 获取用户信息
        User getUserPersonal();
    }

    interface View extends BaseContract.View<Presenter> {
        String getUserId();

        // 加载数据完成
        void onLoadDone(User user);

        // 是否发起聊天
        void allowSayHello(boolean isAllow);

        // 设置关注状态
        void setFollowStatus(boolean isFollow);
    }
}
