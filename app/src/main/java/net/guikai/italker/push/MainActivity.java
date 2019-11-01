package net.guikai.italker.push;

import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.guikai.italker.common.app.BaseActivity;
import net.guikai.italker.common.widget.PortraitView;
import net.qiujuer.genius.ui.widget.FloatActionButton;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.appbar)
    View mLayAppbar;

    @BindView(R.id.im_portrait)
    PortraitView mPortrait;

    @BindView(R.id.txt_title)
    TextView mTitle;

    @BindView(R.id.lay_container)
    FrameLayout mContainer;

    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    @BindView(R.id.btn_action)
    FloatActionButton mAction;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

}
