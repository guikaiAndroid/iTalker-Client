package net.guikai.italker.factory.presenter;

/**
 * Description: 所有Presenter的基类
 * Crete by Anding on 2019-09-20
 */
public class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter {

    private T mView;

    public BasePresenter(T view) {
        setView(view);
    }

    /**
     * 设置一个view, 子类可以复写该方法
     */
    @SuppressWarnings("unchecked")
    protected void setView(T view) {
        this.mView = view;
        this.mView.setPresenter(this);
    }

    /**
     * 让子类可以获取view进行处理
     * 不允许复写
     * @return View
     */
    protected final T getView() {
        return mView;
    }

    @Override
    public void start() {
        // 开始的时候进行Loading调用
        T view = mView;
        if (view != null) {
            view.showLoading();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void destroy() {
        T view = mView;
        mView = null;
        if (view != null) {
            // 把Presenter设置为NULL
            view.setPresenter(null);
        }
    }

}
