package net.guikai.italker.factory.data;

import java.util.List;

/**
 * Description: 基础的数据库数据源接口定义
 * Crete by Anding on 2020-04-16
 */
public interface DbDataSource<Data> extends DataSource {
    /**
     * 有一个基本的数据源加载方法
     *
     * @param callback 传递一个callback回调，一般回调到Presenter
     */
    void load(SuccessCallback<List<Data>> callback);
}
