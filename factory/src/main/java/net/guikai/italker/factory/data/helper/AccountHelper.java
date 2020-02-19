package net.guikai.italker.factory.data.helper;

import android.text.TextUtils;

import net.guikai.italker.factory.Factory;
import net.guikai.italker.factory.R;
import net.guikai.italker.factory.data.DataSource;
import net.guikai.italker.factory.model.api.RspModel;
import net.guikai.italker.factory.model.api.account.AccountRspModel;
import net.guikai.italker.factory.model.api.account.LoginModel;
import net.guikai.italker.factory.model.api.account.RegisterModel;
import net.guikai.italker.factory.model.db.User;
import net.guikai.italker.factory.net.Network;
import net.guikai.italker.factory.net.RemoteService;
import net.guikai.italker.factory.persistence.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description: 用户请求实现类
 * Crete by Anding on 2020-02-15
 */
public class AccountHelper {

    /**
     * 注册的接口，异步的调用
     *
     * @param model  传递一个注册的Model进来
     * @param callBack  成功与失败的接口回送
     */
    public static void register(final RegisterModel model, final DataSource.CallBack<User> callBack) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountRegister(model);
        //异步请求
        call.enqueue(new AccountRspCallback(callBack));
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                callBack.onDataNotAvailable(R.string.data_rsp_error_parameters);
//            }
//        }.start();
    }

    /**
     * 登录的调用
     *
     * @param model    登录的Model
     * @param callback 成功与失败的接口回送
     */
    public static void login(final LoginModel model, final DataSource.CallBack<User> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountLogin(model);
        // 异步的请求
        call.enqueue(new AccountRspCallback(callback));
    }

    /**
     * 对设备Id进行绑定的操作
     *
     * @param callback Callback
     */
    public static void bindPush(final DataSource.CallBack<User> callback) {
        // 检查是否为空
        String pushId = Account.getPushId();
        if (TextUtils.isEmpty(pushId))
            return;
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        Call<RspModel<AccountRspModel>> call = service.accountBind(pushId);
        call.enqueue(new AccountRspCallback(callback));
    }

    /**
     * 请求的得到的回调部分封装类
     */
    public static class AccountRspCallback implements Callback<RspModel<AccountRspModel>> {

        final DataSource.CallBack<User> callback;

        AccountRspCallback(DataSource.CallBack<User> callBack) {
            this.callback = callBack;
        }

        @Override
        public void onResponse(Call<RspModel<AccountRspModel>> call, Response<RspModel<AccountRspModel>> response) {
            // 从请求成功返回
            // 从返回中得到我们的全局Model, 内部是使用的Gson解析
            RspModel<AccountRspModel> rspModel = response.body();
            if (rspModel.success()) {
                // 拿到实体部分
                AccountRspModel accountRspModel = rspModel.getResult();
                // 获取我的信息
                User user = accountRspModel.getUser();

                // 数据库持久化
                DbHelper.save(User.class, user);

                // 同步到XML持久化中
                Account.login(accountRspModel);

                // 判断绑定状态，是否绑定设备
                if (accountRspModel.isBind()) {
                    // 设置绑定状态为True
                    Account.setBind(true);
                    // 然后返回
                    if (callback != null)
                        callback.onDataLoaded(user);
                } else {
                    // 没有绑定则进行绑定的唤起，调用接口
                    bindPush(callback);
                }
            } else {
                // 错误解析
                Factory.decodeRspCode(rspModel, callback);
            }
        }

        @Override
        public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {
            //网络请求失败
            if (call != null) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        }
    }
}
