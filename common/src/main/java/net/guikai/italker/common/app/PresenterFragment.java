package net.guikai.italker.common.app;

import android.content.Context;

import net.guikai.italker.common.presenter.BaseContract;

/**
 * Description: PresenterFragment在BaseFragment上进行MVP的封装
 * Crete by Anding on 2019-09-20
 */
public abstract class PresenterFragment<Presenter extends BaseContract.Presenter>
        extends BaseFragment implements BaseContract.View<Presenter> {

    protected Presenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 在界面onAttach之后就触发初识Presenter
        initPresenter();
    }

    protected abstract Presenter initPresenter();

    @Override
    public void showError(int str) {
        // 显示错误，优先使用占位布局
        if (mPlaceHolderView != null) {
            mPlaceHolderView.triggerError(str);
        } else {
            BaseApplication.showToast(str);
        }
    }

    @Override
    public void showLoading() {
        if (mPlaceHolderView!= null) {
            mPlaceHolderView.triggerLoading();
        }
    }

    @Override
    public void setPresenter(Presenter presenter) {
        // View中赋值Presenter
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null) {
            mPresenter.destroy();
        }
    }
}
