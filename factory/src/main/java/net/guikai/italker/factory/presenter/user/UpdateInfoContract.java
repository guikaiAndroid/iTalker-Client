package net.guikai.italker.factory.presenter.user;

import net.guikai.italker.common.presenter.BaseContract;

/**
 * Description: 更新用户信息契约
 * Crete by Anding on 2020-02-20
 */
public interface UpdateInfoContract {
    interface Presenter extends BaseContract.Presenter {
        // 更新
        void update(String photoFilePath, String desc, boolean isMan);
    }

    interface View extends BaseContract.View<Presenter> {
        // 回调成功
        void updateSucceed();
    }
}
