package net.guikai.italker.push;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;

import net.guikai.italker.common.app.BaseActivity;
import net.guikai.italker.factory.persistence.Account;
import net.guikai.italker.push.activities.AccountActivity;
import net.guikai.italker.push.activities.MainActivity;
import net.guikai.italker.push.frags.assist.PermissionsFragment;
import net.qiujuer.genius.res.Resource;
import net.qiujuer.genius.ui.compat.UiCompat;

/**
 * Description: 启动页
 * Crete by Anding on 2020-02-18
 */
public class LaunchActivity extends BaseActivity {

    // Drawable
    private ColorDrawable mBgDrawable;
    // 判断是否已经得到PushId
    private boolean mAlreadyGotPushReceiverId = false;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        // 拿到根布局
        View root = findViewById(R.id.activity_launch);
        // 获取颜色
        int color = UiCompat.getColor(getResources(), R.color.colorPrimary);
        // 创建一个Drawable
        ColorDrawable drawable = new ColorDrawable(color);
        // 设置背景
        root.setBackground(drawable);
        mBgDrawable = drawable;
    }

    @Override
    protected void initData() {
        super.initData();

        // 检查等待状态
        startAnim(0.5f, new Runnable() {
            @Override
            public void run() {
               // 检查等待状态
                waitPushReceiveId();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 判断是否已经得到推送Id，如果已经得到则进行跳转操作，
        // 在操作中检测权限状态
        if (mAlreadyGotPushReceiverId) {
            reallySkip();
        }
    }

    /**
     * 等待个推框架对我们的PushId设置好值
     */
    private void waitPushReceiveId() {
        if (Account.isLogin()) {
            // 已经登录情况下，判断是否绑定
            // 如果没有绑定则等待广播接收器进行绑定
            if (Account.isBind()) {
                waitPushReceiverIdDone();
                return;
            }
        } else {
            // 没有登录
            // 如果拿到PushId 直接跳转
            if (!TextUtils.isEmpty(Account.getPushId())) {
                // 跳转
                waitPushReceiverIdDone();
                return;
            }
        }

        // 没有登录 也没有拿到PushId 循环等待个推的id
        getWindow().getDecorView().postDelayed(this::waitPushReceiveId, 500);
    }

    /**
     * 在跳转之前需要把剩下的50%进行完成
     */
    private void waitPushReceiverIdDone() {
        // 标志已经得到PushId
        mAlreadyGotPushReceiverId = true;
        startAnim(1f,this::reallySkip);
    }

    /**
     * 真实的跳转
     */
    private void reallySkip() {
        // 权限检测，跳转
        if (PermissionsFragment.haveAll(this, getSupportFragmentManager())) {
            if (Account.isLogin()) {
                MainActivity.show(this);
            } else {
                AccountActivity.show(this);
            }
            finish();
        }
    }

    private void startAnim(float endProgress, final Runnable endCallback) {
        // 获取一个最终的颜色
        int finalColor = Resource.Color.WHITE;
        // 运算当前进度的颜色
        ArgbEvaluator evaluator = new ArgbEvaluator();
        // 运算当前进度条颜色
        int enColor = (int) evaluator.evaluate(endProgress, mBgDrawable.getColor(), finalColor);
        // 构建一个属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofObject(this, property, evaluator, enColor);
        valueAnimator.setIntValues(mBgDrawable.getColor(),enColor);
        valueAnimator.setDuration(1500);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 结束时触发
                endCallback.run();
            }
        });
        valueAnimator.start();
    }

    private final Property<LaunchActivity, Object> property = new Property<LaunchActivity, Object>(Object.class, "color") {
        @Override
        public Object get(LaunchActivity object) {
            return object.mBgDrawable.getColor();
        }

        @Override
        public void set(LaunchActivity object, Object value) {
            object.mBgDrawable.setColor((Integer) value);
        }
    };
}
