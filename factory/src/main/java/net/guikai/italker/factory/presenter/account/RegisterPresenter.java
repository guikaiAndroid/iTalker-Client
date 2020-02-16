package net.guikai.italker.factory.presenter.account;

import android.text.TextUtils;

import net.guikai.italker.common.Common;
import net.guikai.italker.common.presenter.BasePresenter;
import net.guikai.italker.factory.R;
import net.guikai.italker.factory.data.DataSource;
import net.guikai.italker.factory.data.helper.AccountHelper;
import net.guikai.italker.factory.model.api.account.RegisterModel;
import net.guikai.italker.factory.model.db.User;
import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import java.util.regex.Pattern;

/**
 * Description: 注册逻辑实现类
 * Crete by Anding on 2020-02-15
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View>
        implements RegisterContract.Presenter, DataSource.CallBack<User> {

    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void register(String phone, String name, String password) {
        // 调用开始方法，在Base中默认开始启动Loading
        start();

        // 得到View接口
        RegisterContract.View view = getView();

        // 校验
        if (!checkMobile(phone)) {
            // 提示
            view.showError(R.string.data_account_register_invalid_parameter_mobile);
        } else if (name.length() < 2) {
            // 姓名需要大于2位
            view.showError(R.string.data_account_register_invalid_parameter_name);
        } else if (password.length() < 6) {
            view.showError(R.string.data_account_register_invalid_parameter_password);
        } else {
            // 开始进行网络请求
            // 构造请求Model, 进行请求调用
            RegisterModel model = new RegisterModel(phone,password,name);
            //进行网络请求
            AccountHelper.register(model,this);
        }
    }

    /**
     * 检查手机号是否合法
     *
     * @param phone 手机号码
     * @return 是否合法
     */
    @Override
    public boolean checkMobile(String phone) {
        //  手机号不能为空，并且满足手机格式
        return !TextUtils.isEmpty(phone)
                && Pattern.matches(Common.Constance.REGEX_MOBILE,phone);
    }

    // 数据成功后的接口回调
    @Override
    public void onDataLoaded(User user) {
        final RegisterContract.View view = getView();
        if (view==null)
            return;
            Run.onUiAsync(new Action() {
                @Override
                public void call() {
                    view.registerSuccess();
                }
            });
    }

    // 数据失败后的接口回调
    @Override
    public void onDataNotAvailable(final int strRes) {
        final RegisterContract.View view = getView();
        if (view==null)
            return;
            Run.onUiAsync(new Action() {
                @Override
                public void call() {
                    view.showError(strRes);
                }
            });
    }
}
