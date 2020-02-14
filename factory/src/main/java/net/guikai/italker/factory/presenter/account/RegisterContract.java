package net.guikai.italker.factory.presenter.account;

import net.guikai.italker.common.presenter.BaseContract;

/**
 * Description: 注册界面的逻辑契约
 * Crete by Anding on 2020-02-14
 */
public interface RegisterContract {

    interface View extends BaseContract.View<Presenter> {
        //注册成功 view的改变
        void registerSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        // 注册逻辑
        void register(String phone, String name,String password);

        // 检查手机号是否正确
        boolean checkMobile(String phone);
    }
}
