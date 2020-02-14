package net.guikai.italker.push.frags.account;

import android.content.Context;

import net.guikai.italker.factory.presenter.account.LoginContract;
import net.guikai.italker.common.app.PresenterFragment;
import net.guikai.italker.factory.presenter.account.LoginPresenter;
import net.guikai.italker.push.R;
import net.guikai.italker.push.activities.AccountTrigger;

import butterknife.OnClick;

/**
 * Description: 登录碎片界面
 * Crete by Anding on 2019-12-22
 */
public class LoginFragment extends PresenterFragment<LoginContract.Presenter>
        implements LoginContract.View  {

    private AccountTrigger mAccountTrigger;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    @OnClick(R.id.txt_go_register)
    void onShowRegisterClick() {
        // 让AccountActivity进行碎片切换
        mAccountTrigger.triggerView();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected LoginContract.Presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void showError(int str) {
        super.showError(str);

    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void loginSuccess() {

    }
}
