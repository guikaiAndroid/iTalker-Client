package net.guikai.italker.factory.data.helper;

import net.guikai.italker.factory.R;
import net.guikai.italker.factory.data.DataSource;
import net.guikai.italker.factory.model.api.RspModel;
import net.guikai.italker.factory.model.api.account.AccountRspModel;
import net.guikai.italker.factory.model.api.account.RegisterModel;
import net.guikai.italker.factory.model.db.User;
import net.guikai.italker.factory.net.Network;
import net.guikai.italker.factory.net.RemoteService;

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
     * @param model
     * @param callBack
     */
    public static void register(final RegisterModel model, final DataSource.CallBack<User> callBack){
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
     * 请求的得到的回调部分封装类
     */
    public static class AccountRspCallback implements Callback<RspModel<AccountRspModel>> {

        final DataSource.CallBack<User> callback;

        public AccountRspCallback(DataSource.CallBack<User> callBack) {
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
                callback.onDataLoaded(user);
            }
        }

        @Override
        public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {
            callback.onDataNotAvailable(R.string.data_network_error);
        }
    }
}
