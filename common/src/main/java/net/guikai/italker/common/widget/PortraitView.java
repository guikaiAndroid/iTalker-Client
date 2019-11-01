package net.guikai.italker.common.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.bumptech.glide.RequestManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Description: 主页ActionBar左方小头像
 * Crete by Anding on 2019-10-31
 */
public class PortraitView extends CircleImageView {
    public PortraitView(Context context) {
        super(context);
    }

    public PortraitView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PortraitView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

//    public void setup(RequestManager manager, Author author) {
//        if (author == null)
//            return;
//        // 进行显示
//        setup(manager, author.getPortrait());
//    }

    public void setup(RequestManager manager,String url) {

    }

    public void setup(RequestManager manager,int resourceId, String url) {
        if (url == "" ){
            url = "";
        }
        manager.load(url)
                .placeholder(resourceId)
                .centerCrop()
                .dontAnimate()// CircleImageView 控件中不能使用渐变动画，会导致显示延迟
                .into(this);
    }

}
