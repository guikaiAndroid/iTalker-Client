package net.guikai.italker.factory.data.helper;

import net.guikai.italker.factory.Factory;
import net.guikai.italker.factory.R;
import net.guikai.italker.factory.data.DataSource;
import net.guikai.italker.factory.model.api.RspModel;
import net.guikai.italker.factory.model.api.user.UserUpdateModel;
import net.guikai.italker.factory.model.card.UserCard;
import net.guikai.italker.factory.model.db.User;
import net.guikai.italker.factory.net.Network;
import net.guikai.italker.factory.net.RemoteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description: 关于用户的网络请求处理
 * Crete by Anding on 2020-02-23
 */
public class UserHelper {

    // 更新用户信息的操作，异步的
    public static void update(UserUpdateModel model, final DataSource.CallBack<UserCard> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<UserCard>> call = service.userUpdate(model);
        // 网络请求
        call.enqueue(new Callback<RspModel<UserCard>>() {

            @Override
            public void onResponse(Call<RspModel<UserCard>> call, Response<RspModel<UserCard>> response) {
                RspModel<UserCard> rspModel = response.body();
                if (rspModel.success()) {
                    UserCard userCard = rspModel.getResult();
                    User user = userCard.build();
                    user.save();
                    // 返回成功
                    callback.onDataLoaded(userCard);
                } else {
                    // 错误情况下进行错误分配
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<UserCard>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

    // 搜索的方法
    public static Call search(String name, final DataSource.CallBack<List<UserCard>> callback) {
        RemoteService service = Network.remote();
        Call<RspModel<List<UserCard>>> call = service.userSearch(name);
        call.enqueue(new Callback<RspModel<List<UserCard>>>() {
            @Override
            public void onResponse(Call<RspModel<List<UserCard>>> call, Response<RspModel<List<UserCard>>> response) {
                RspModel<List<UserCard>> rspModel = response.body();
                if (rspModel.success()) {
                    // 返回数据
                    callback.onDataLoaded(rspModel.getResult());
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<List<UserCard>>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
        // 把当前的调度者返回
        return call;
    }
}
