package net.guikai.italker.push.frags.account;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import net.guikai.italker.common.app.PresenterFragment;
import net.guikai.italker.factory.presenter.account.RegisterContract;
import net.guikai.italker.push.R;
import net.guikai.italker.push.activities.AccountTrigger;
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


    @Override
    protected RegisterContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void registerSuccess() {

    }
}
