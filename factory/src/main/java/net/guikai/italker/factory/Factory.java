package net.guikai.italker.factory;

import android.support.annotation.StringRes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import net.guikai.italker.common.app.BaseApplication;
import net.guikai.italker.factory.data.DataSource;
import net.guikai.italker.factory.data.group.GroupCenter;
import net.guikai.italker.factory.data.group.GroupDispatcher;
import net.guikai.italker.factory.data.message.MessageCenter;
import net.guikai.italker.factory.data.message.MessageDispatcher;
import net.guikai.italker.factory.data.user.UserCenter;
import net.guikai.italker.factory.data.user.UserDispatcher;
import net.guikai.italker.factory.model.api.RspModel;
import net.guikai.italker.factory.persistence.Account;
import net.guikai.italker.factory.utils.DBFlowExclusionStrategy;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Description:
 * Crete by Anding on 2020-02-15
 */
public class Factory {
    public static final String TAG = Factory.class.getSimpleName();

    //单例模式
    private static final Factory instance;
    // 全局的线程池
    private final Executor executor;
    // 全局的Gson对象
    private final Gson gson;

    static {
        instance = new Factory();
    }

    private Factory() {
        // 新建一个包含4个线程的线程池
        executor = Executors.newFixedThreadPool(4);
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                // 设置一个过滤器，数据库级别的Model不进行Json转换
                .setExclusionStrategies(new DBFlowExclusionStrategy())
                .create();
    }

    public static void setup() {
        // 初始化数据库
        FlowManager.init(new FlowConfig.Builder(app())
                .openDatabasesOnInit(true)  // 数据库初始化的时候就开始打开
                .build());

        // 持久化的数据进行初始化
        Account.load(app());
    }

    /**
     * 返回全局的Application
     *
     * @return Application
     */
    public static BaseApplication app() {
        return BaseApplication.getInstance();
    }

    /**
     * 异步运行的方法
     *
     * @param runnable Runnable
     */
    public static void runOnAsync(Runnable runnable) {
        // 拿到单例，拿到线程池，然后异步执行
        instance.executor.execute(runnable);
    }

    /**
     * 返回一个全局的Gson，在这可以进行Gson的一些全局的初始化
     *
     * @return Gson
     */
    public static Gson getGson() {
        return instance.gson;
    }

    public static void decodeRspCode(RspModel model, DataSource.FailedCallback callback) {
        if (model == null)
            return;

        switch (model.getCode()) {
            case RspModel.SUCCEED:
                return;
            case RspModel.ERROR_SERVICE:
                decodeRspCode(R.string.data_rsp_error_service, callback);
                break;
            case RspModel.ERROR_NOT_FOUND_USER:
                decodeRspCode(R.string.data_rsp_error_not_found_user, callback);
                break;
            case RspModel.ERROR_NOT_FOUND_GROUP:
                decodeRspCode(R.string.data_rsp_error_not_found_group, callback);
                break;
            case RspModel.ERROR_NOT_FOUND_GROUP_MEMBER:
                decodeRspCode(R.string.data_rsp_error_not_found_group_member, callback);
                break;
            case RspModel.ERROR_CREATE_USER:
                decodeRspCode(R.string.data_rsp_error_create_user, callback);
                break;
            case RspModel.ERROR_CREATE_GROUP:
                decodeRspCode(R.string.data_rsp_error_create_group, callback);
                break;
            case RspModel.ERROR_CREATE_MESSAGE:
                decodeRspCode(R.string.data_rsp_error_create_message, callback);
                break;
            case RspModel.ERROR_PARAMETERS:
                decodeRspCode(R.string.data_rsp_error_parameters, callback);
                break;
            case RspModel.ERROR_PARAMETERS_EXIST_ACCOUNT:
                decodeRspCode(R.string.data_rsp_error_parameters_exist_account, callback);
                break;
            case RspModel.ERROR_PARAMETERS_EXIST_NAME:
                decodeRspCode(R.string.data_rsp_error_parameters_exist_name, callback);
                break;
            case RspModel.ERROR_ACCOUNT_TOKEN:
                BaseApplication.showToast(R.string.data_rsp_error_account_token);
                instance.logout();
                break;
            case RspModel.ERROR_ACCOUNT_LOGIN:
                decodeRspCode(R.string.data_account_login_error_validate, callback);
                break;
            case RspModel.ERROR_ACCOUNT_REGISTER:
                decodeRspCode(R.string.data_rsp_error_account_register, callback);
                break;
            case RspModel.ERROR_ACCOUNT_NO_PERMISSION:
                decodeRspCode(R.string.data_rsp_error_account_no_permission, callback);
                break;
            case RspModel.ERROR_UNKNOWN:
            default:
                decodeRspCode(R.string.data_rsp_error_unknown, callback);
                break;

        }
    }

    private static void decodeRspCode(@StringRes final int resId,
                                      final DataSource.FailedCallback callback) {
        if (callback != null)
            callback.onDataNotAvailable(resId);
    }

    /**
     * 收到账户退出的消息 需要进行账户退出重新登录
     */
    private void logout() {

    }

    /**
     * 获取一个用户中心的实现类
     *
     * @return 用户中心的规范接口
     */
    public static UserCenter getUserCenter() {
        return UserDispatcher.instance();
    }

    /**
     * 获取一个消息中心的实现类
     *
     * @return 消息中心的规范接口
     */
    public static MessageCenter getMessageCenter() {
        return MessageDispatcher.instance();
    }

    /**
     * 获取一个群处理中心的实现类
     *
     * @return 群中心的规范接口
     */
    public static GroupCenter getGroupCenter() {
        return GroupDispatcher.instance();
    }


}
