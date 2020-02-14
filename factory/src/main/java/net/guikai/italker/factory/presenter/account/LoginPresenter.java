package net.guikai.italker.factory.presenter.account;

import net.guikai.italker.common.presenter.BasePresenter;

/**
 * Description: Presenter登录逻辑实现
 * Crete by Anding on 2019-12-23
 */
public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter{

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String phone, String password) {

    }
}
