package net.guikai.italker.factory.data;

import android.support.annotation.StringRes;

/**
 * Description: 数据源接口标准
 * Crete by Anding on 2020-02-15
 */
public interface DataSource {

    /**
     * 同时包括了成功与失败的回调接口
     *
     * @param <T> 任意类型`
     */
    interface CallBack<T> extends SuccessCallback<T>, FailedCallback {

    }

    /**
     * 数据请求成功接口
     *
     * @param <T> 请求成功回调的任意类型
     */
    interface SuccessCallback<T> {
        void onDataLoaded(T t);
    }

    /**
     * 数据请求失败接口
     */
    interface FailedCallback {
        void onDataNotAvailable(@StringRes int strRes);
    }

    /**
     * 销毁操作
     */
    void dispose();
}
