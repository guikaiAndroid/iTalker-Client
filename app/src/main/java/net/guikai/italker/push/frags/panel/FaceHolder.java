package net.guikai.italker.push.frags.panel;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;

import net.guikai.italker.common.widget.recycler.BaseRecyclerAdapter;
import net.guikai.italker.face.Face;
import net.guikai.italker.push.R;

import butterknife.BindView;

/**
 * Description:
 * Crete by Anding on 2020/9/1
 */
public class FaceHolder extends BaseRecyclerAdapter.ViewHolder<Face.Bean> {
    @BindView(R.id.im_face)
    ImageView mFace;

    public FaceHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void onBind(Face.Bean bean) {
        if (bean != null
                // drawable 资源 id
                && ((bean.preview instanceof Integer)
                // face zip 包资源路径
                || bean.preview instanceof String))
            Glide.with(itemView.getContext())
                    .load(bean.preview)
                    .asBitmap()
                    .format(DecodeFormat.PREFER_ARGB_8888) //设置解码格式8888，保证清晰度
                    .placeholder(R.drawable.default_face)
                    .into(mFace);
    }
}
