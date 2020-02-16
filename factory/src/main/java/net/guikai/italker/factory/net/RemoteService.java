package net.guikai.italker.factory.net;

import net.guikai.italker.factory.model.api.RspModel;
import net.guikai.italker.factory.model.api.account.AccountRspModel;
import net.guikai.italker.factory.model.api.account.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

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
}
