package net.guikai.italker.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.guikai.italker.common.R;
import net.guikai.italker.common.widget.convention.PlaceHolderView;
import net.qiujuer.genius.ui.widget.Loading;

/**
 * Description: 简单的占位控件，实现了显示一个空的图片显示，可以和MVP配合显示没有数据，正在加载等状态
 * Crete by Anding on 2019-09-18
 */
@SuppressWarnings("unused")
public class EmptyView extends LinearLayout implements PlaceHolderView {
    private ImageView mEmptyImg;
    private TextView mStatusText;
    private Loading mLoading;

    private int[] mDrawableIds = new int[]{0, 0};
    private int[] mTextIds = new int[]{0, 0, 0};

    private View[] mBindViews;

    public EmptyView(Context context) {
        super(context);
        init(null, 0);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(null, 0);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attributeSet, int defStyle) {
        inflate(getContext(), R.layout.lay_empty, this);
        mEmptyImg = findViewById(R.id.im_empty);
        mStatusText = findViewById(R.id.txt_empty);
        mLoading = findViewById(R.id.loading);

        // Load attributes
        TypedArray a = getContext().obtainStyledAttributes(attributeSet, R.styleable.EmptyView, defStyle, 0);

        mDrawableIds[0] = a.getInt(R.styleable.EmptyView_comEmptyDrawable, R.drawable.status_empty);
        mDrawableIds[1] = a.getInt(R.styleable.EmptyView_comErrorDrawable, R.drawable.status_empty);
        mTextIds[0] = a.getInt(R.styleable.EmptyView_comEmptyText, R.string.prompt_empty);
        mTextIds[1] = a.getInt(R.styleable.EmptyView_comErrorDrawable, R.string.prompt_error);
        mTextIds[2] = a.getInt(R.styleable.EmptyView_comLoadingText, R.string.prompt_loading);

        a.recycle();
    }

    /**
     * 绑定一系列数据显示的布局
     * 当前布局隐藏时(也就是有数据) 自动显示绑定的数据界面
     * 而在数据加载、数据错误时自动显示Loading本视图,并隐藏数据界面
     *
     * @param views 数据显示的布局
     */
    public void bind(View... views) {
        this.mBindViews = views;
    }

    /**
     * 更改绑定布局的显示状态
     *
     * @param visible
     */
    private void changeBindViewVisibility(int visible) {
        final View[] views = mBindViews;
        if (views == null || views.length == 0) return;
        for (View view : views) {
            view.setVisibility(visible);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerEmpty() {
        mLoading.setVisibility(GONE);
        mLoading.stop();
        mEmptyImg.setImageResource(mDrawableIds[0]);
        mStatusText.setText(mTextIds[0]);
        mEmptyImg.setVisibility(VISIBLE);
        setVisibility(VISIBLE);
        changeBindViewVisibility(GONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerNetError() {
        mLoading.setVisibility(GONE);
        mLoading.stop();
        mEmptyImg.setImageResource(mDrawableIds[1]);
        mStatusText.setText(mTextIds[1]);
        mEmptyImg.setVisibility(VISIBLE);
        setVisibility(VISIBLE);
        changeBindViewVisibility(GONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerError(@StringRes int strRes) {
        setVisibility(VISIBLE);
        changeBindViewVisibility(GONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerLoading() {
        mLoading.setVisibility(GONE);
        mStatusText.setText(mTextIds[2]);
        setVisibility(VISIBLE);
        mLoading.setVisibility(VISIBLE);
        mLoading.start();
        changeBindViewVisibility(GONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerOk() {
        setVisibility(GONE);
        changeBindViewVisibility(VISIBLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerOkOrEmpty(boolean isOk) {
        if (isOk)
            triggerOk();
        else triggerEmpty();
    }
}
