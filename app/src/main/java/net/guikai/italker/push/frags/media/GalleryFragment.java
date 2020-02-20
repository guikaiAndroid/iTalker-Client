package net.guikai.italker.push.frags.media;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import net.guikai.italker.common.widget.GalleryView;
import net.guikai.italker.push.R;

import java.util.Objects;

/**
 * Description: 图片选择Fragment
 * Crete by Anding on 2020-02-20
 */
public class GalleryFragment extends BottomSheetDialogFragment
        implements GalleryView.SelectedChangeListener {

    private GalleryView mGallery;
    private OnSelectedListener mListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 返回一个我们复写的
        return new BottomSheetDialog(Objects.requireNonNull(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final Context context = Objects.requireNonNull(getActivity());
        final FrameLayout frameLayout = new FrameLayout(context);
        inflater.inflate(R.layout.fragment_gallery, frameLayout, true);
        mGallery = frameLayout.findViewById(R.id.galleryView);
        return frameLayout;
    }

    @Override
    public void onStart() {
        super.onStart();
        mGallery.setup(getLoaderManager(), this);
    }

    @Override
    public void onSelectedCountChanged(int count) {
        // 如果选中的一张图片
        if (count > 0) {
            // 隐藏自己
            dismiss();
            if (mListener != null) {
                // 得到所有的选中的图片的路径
                String[] paths = mGallery.getSelectedPath();
                // 返回第一张
                mListener.onSelectedImage(paths[0]);
                // 取消和唤起者之间的应用，加快内存回收
                mListener = null;
            }
        }
    }

    /**
     * 设置事件监听，并返回自己
     *
     * @param listener OnSelectedListener
     * @return GalleryFragment
     */
    public GalleryFragment setListener(OnSelectedListener listener) {
        mListener = listener;
        return this;
    }

    /**
     * 选中图片的监听器
     */
    public interface OnSelectedListener {
        void onSelectedImage(String path);
    }
}
