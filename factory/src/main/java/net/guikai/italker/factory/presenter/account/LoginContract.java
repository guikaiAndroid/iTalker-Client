package net.guikai.italker.factory.presenter.account;

import net.guikai.italker.factory.presenter.BaseContract;

/**
 * Description: 登录契约接口
 * Crete by Anding on 2019-12-23
 */
public interface LoginContract {

    interface View extends BaseContract.View<Presenter> {
        // 登录成功
        void loginSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        // 发起一个登录操(逻辑操作)
        void login(String phone,String password);
        // 检查手机号是否正确
        boolean checkMobile(String phone);
    }
}
