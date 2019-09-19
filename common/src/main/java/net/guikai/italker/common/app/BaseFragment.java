package net.guikai.italker.common.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.guikai.italker.common.widget.convention.PlaceHolderView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description: 所有有Fragment的基类
 * Crete by Anding on 2019-09-18
 */
public abstract class BaseFragment extends Fragment {

    protected View mRoot;
    protected Unbinder mRootUnBinder;
    protected PlaceHolderView mPlaceHolderView;
    //标示是否第一次初始化数据
    protected boolean mIsFirstInitData = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 初始化参数
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null) {
            int layId = getContentLayoutId();
            //初始化当前的布局，但是不在创建时就添加到container里面
            View root = inflater.inflate(layId,container,false);
            initWidget(root);
            mRoot = root;
        } else {
            if (mRoot.getParent() != null) {
                ((ViewGroup) mRoot.getParent()).removeView(mRoot);
            }
        }
        return mRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mIsFirstInitData) {
            //触发第一次以后就不会触发
            mIsFirstInitData = false;
            //触发一次
            onFirstInit();
        }

        //当View创建完成之后初始化数据
        initData();
    }

    /**
     * 初始化相关参数
     * @param arguments 获取Bundle
     */
    protected void initArgs(Bundle arguments) {

    }

    /**
     * 子类必须实现，得到当前界面资源Id
     * @return 资源文件Id
     */
    @LayoutRes
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     * @param root
     */
    protected void initWidget(View root) {
        mRootUnBinder = ButterKnife.bind(this,root);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 当首次加载布局的时候调用此方法
     */
    protected void onFirstInit() {

    }

    /**
     * 返回按键触发时调用
     *
     * @return 返回True代表我已处理返回逻辑，Activity不用自己finish。
     * 返回False代表我没有处理逻辑，Activity自己走自己的逻辑
     */
    public boolean onBackPressed() {
        return false;
    }

    /**
     * 设置占位布局
     * @param placeHolderView 继承实现占位符布局规范的View布局
     */
    public void setPlaceHolderView(PlaceHolderView placeHolderView) {
        this.mPlaceHolderView = placeHolderView;
    }

}
