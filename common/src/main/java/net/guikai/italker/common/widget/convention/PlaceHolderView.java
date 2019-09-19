package net.guikai.italker.common.widget.convention;

import android.support.annotation.StringRes;

/**
 * Description: 基础的占位布局接口定义
 * Crete by Anding on 2019-09-18
 */
public interface PlaceHolderView {

    /**
     * 没有数据时
     * 显示空布局，隐藏当前的布局
     */
    void triggerEmpty();

    /**
     * 网络出现异常
     * 显示一个网络错误的图标
     */
    void triggerNetError();

    /**
     * 加载错误，显示错误信息
     * @param strRes 错误信息
     */
    void triggerDataError(@StringRes int strRes);

    /**
     * 显示正在加载的状态
     */
    void triggerLoading();

    /**
     * 数据如果加载成功
     * 调用该方法隐藏当前占位布局
     */
    void triggerOk();

    /**
     * 该方法如果传入的isOk为True则为成功状态，
     * 此时隐藏布局，反之显示空数据布局
     *
     * @param isOk 是否加载成功数据
     */
    void triggerOkOrEmpty(boolean isOk);

}
