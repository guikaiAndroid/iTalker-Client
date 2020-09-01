package net.guikai.italker.push.frags.panel;

import android.view.View;

import net.guikai.italker.common.widget.recycler.BaseRecyclerAdapter;
import net.guikai.italker.face.Face;
import net.guikai.italker.push.R;

import java.util.List;

/**
 * Description:
 * Crete by Anding on 2020/9/1
 */
public class FaceAdapter extends BaseRecyclerAdapter<Face.Bean> {
    public FaceAdapter(List<Face.Bean> beans, BaseRecyclerAdapter.AdapterListener<Face.Bean> listener) {
        super(beans, listener);
    }

    @Override
    protected int getItemViewType(int position, Face.Bean bean) {
        return R.layout.cell_face;
    }

    @Override
    protected ViewHolder<Face.Bean> onCreateViewHolder(View root, int viewType) {
        return new FaceHolder(root);
    }
}
