package net.guikai.italker.factory.net;

import net.guikai.italker.factory.model.api.RspModel;
import net.guikai.italker.factory.model.api.account.AccountRspModel;
import net.guikai.italker.factory.model.api.account.LoginModel;
import net.guikai.italker.factory.model.api.account.RegisterModel;
import net.guikai.italker.factory.model.api.user.UserUpdateModel;
import net.guikai.italker.factory.model.card.UserCard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Description: retrofit请求的所有的接口
 * Crete by Anding on 2020-02-15
 */
public interface RemoteService {

    /**
     * 注册接口
     *
     * @param model 传入的是RegisterModel post的数据
     * @return 返回的是ResModel<AccountRspModel>
     */
    @POST("account/register")
    Call<RspModel<AccountRspModel>> accountRegister(@Body RegisterModel model);

    /**
     * 登录接口
     *
     * @param model LoginModel
     * @return RspModel<AccountRspModel>
     */
    @POST("account/login")
    Call<RspModel<AccountRspModel>> accountLogin(@Body LoginModel model);


    /**
     * 绑定设备Id
     *
     * @param pushId 设备Id
     * @return RspModel<AccountRspModel>
     */
    @POST("account/bind/{pushId}")
    Call<RspModel<AccountRspModel>> accountBind(@Path(encoded = true, value = "pushId") String pushId);

    // 用户更新的接口
    @PUT("user")
    Call<RspModel<UserCard>> userUpdate(@Body UserUpdateModel model);

    // 用户搜索的接口
    @GET("user/search/{name}")
    Call<RspModel<List<UserCard>>> userSearch(@Path("name") String name);

    // 用户关注接口
    @PUT("user/follow/{userId}")
    Call<RspModel<UserCard>> userFollow(@Path("userId") String userId);

    // 获取联系人列表
    @GET("user/contact")
    Call<RspModel<List<UserCard>>> userContacts();

    // 查询某人的信息
    @GET("user/{userId}")
    Call<RspModel<UserCard>> userFind(@Path("userId") String userId);

}
