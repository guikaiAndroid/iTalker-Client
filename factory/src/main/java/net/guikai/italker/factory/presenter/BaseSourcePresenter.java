package net.guikai.italker.factory.presenter;

import net.guikai.italker.factory.data.DataSource;
import net.guikai.italker.factory.data.DbDataSource;

import java.util.List;

/**
 * Description: 基础的仓库源的Presenter定义
 * Crete by Anding on 2020-04-16
 */
public abstract class BaseSourcePresenter<Data, ViewModel,
        Source extends DbDataSource<Data>,
        View extends BaseContract.RecyclerView>
        extends BaseRecyclerPresenter<ViewModel, View>
        implements DataSource.SuccessCallback<List<Data>> {

    protected Source mSource;

    public BaseSourcePresenter(Source source, View view) {
        super(view);
        this.mSource = source;
    }

    @Override
    public void start() {
        super.start();
        if (mSource != null)
            mSource.load(this);
    }

    @Override
    public void destroy() {
        super.destroy();
        mSource.dispose();
        mSource = null;
    }
}
