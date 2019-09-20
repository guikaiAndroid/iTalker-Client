package net.guikai.italker.common.widget.recycler;

/**
 * Description:
 * Crete by Anding on 2019-09-19
 */
public interface AdapterCallback<Data> {
    void update(Data data, BaseRecyclerAdapter.ViewHolder<Data> holder);
}
