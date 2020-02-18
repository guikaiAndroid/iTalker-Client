package net.guikai.italker.push.frags.account;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import net.guikai.italker.common.app.PresenterFragment;
import net.guikai.italker.factory.presenter.account.RegisterContract;
import net.guikai.italker.factory.presenter.account.RegisterPresenter;
import net.guikai.italker.push.R;
import net.guikai.italker.push.activities.AccountTrigger;
import net.guikai.italker.push.activities.MainActivity;
import net.qiujuer.genius.ui.widget.Loading;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description: 注册碎片
 * Crete by Anding on 2019-12-22
 */
public class RegisterFragment extends PresenterFragment<RegisterContract.Presenter>
        implements RegisterContract.View {

    private AccountTrigger mAccountTrigger;

    @BindView(R.id.edit_phone)
    EditText mPhone;
    @BindView(R.id.edit_name)
    EditText mName;
    @BindView(R.id.edit_password)
    EditText mPassword;

    @BindView(R.id.loading)
    Loading mLoading;

    @BindView(R.id.btn_submit)
    Button mSubmit;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 从Activity中拿到引用
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_register;
    }

    @OnClick(R.id.txt_go_login)
    void onShowLoginClick() {

        // 让AccountActivity进行界面切换
        mAccountTrigger.triggerView();
    }

    @OnClick(R.id.btn_submit)
    void onSubmitClick() {
        String phone = mPhone.getText().toString();
        String name = mName.getText().toString();
        String password = mPassword.getText().toString();
        // 调用P层进行逻辑处理
        mPresenter.register(phone,name,password);
    }


    @Override
    protected RegisterContract.Presenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public void showError(int str) {
        super.showError(str);
        // 显示此错误，一定是逻辑结束后调用

        // 停止Loading
        mLoading.stop();
        // 让用户可以再次输入
        mPhone.setEnabled(true);
        mName.setEnabled(true);
        mPassword.setEnabled(true);
        // 提交按钮 可以继续点击
        mSubmit.setEnabled(true);
    }

    @Override
    public void showLoading() {
        super.showLoading();

        // 正在进行后台注册操作，界面不可操作
        // 开始显示Loading
        mLoading.start();
        mPhone.setEnabled(false);
        mName.setEnabled(false);
        mPassword.setEnabled(false);
        // 提交按钮 可以继续点击
        mSubmit.setEnabled(false);
    }

    @Override
    public void registerSuccess() {
        // 当注册成功 是界面发生的改变
        // 这个时候需要跳转到主界面去
        MainActivity.show(getContext());
        // 关闭当前页面
        getActivity().finish();
    }
}
