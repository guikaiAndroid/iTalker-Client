package net.guikai.italker.common.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import net.guikai.italker.common.widget.convention.PlaceHolderView;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Description:所有活动的基类
 * Crete by Anding on 2019-09-18
 */
public abstract class BaseActivity extends AppCompatActivity {

    PlaceHolderView mPlaceHolderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 界面未初始化之前调用的初始化Window
        initWindows();

        if (initArgs(getIntent().getExtras())) {
            // 得到界面Id并设置到Activity界面中
            int layId = getContentLayoutId();
            setContentView(layId);
            initBefore();
            initWidget();
            initData();
        } else {
            finish();
        }
    }

    /**
     * 初始化Windows窗口
     */
    protected void initWindows() {

    }

    /**
     * 初始化控件之前调用
     */
    protected void initBefore() {

    }

    /**
     * 初始化相关参数
     * @param bundle 参数Bundle
     * @return 如果参数正确返回true 错误返回false
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    /**
     * 子类必须实现，得到当前界面资源Id
     * @return 资源文件Id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化View、Widget控件绑定
     */
    protected void initWidget() {
        ButterKnife.bind(this);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        // 当点击界面导航返回时，finish当前Activity
        finish();
        return super.onSupportNavigateUp();
    }

    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        //判断是否为空
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment:fragments) {
                //判断碎片是否为我们能够处理的Fragment类型
                if (fragment instanceof BaseFragment) {
                    //判断是否拦截了返回按钮
                    if (((BaseFragment) fragment).onBackPressed()) {
                        //如果返回true标识fragment已经做处理
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
        finish();
    }

    /**
     * 设置占位布局
     *
     * @param placeHolderView 继承了占位布局规范的View
     */
    public void setPlaceHolderView(PlaceHolderView placeHolderView) {
        this.mPlaceHolderView = placeHolderView;
    }

}
