package net.guikai.italker.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Description:
 * Crete by Anding on 2020-02-15
 */
public class Factory {
    public static final String TAG = Factory.class.getSimpleName();

    //单例模式
    public static final Factory instance;
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
               .create();
    }

    /**
     * 返回一个全局的Gson，在这可以进行Gson的一些全局的初始化
     *
     * @return Gson
     */
    public static Gson getGson() {
        return instance.gson;
    }

}
