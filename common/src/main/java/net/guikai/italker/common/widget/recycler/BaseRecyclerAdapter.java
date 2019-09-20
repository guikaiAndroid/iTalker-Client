package net.guikai.italker.common.widget.recycler;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.guikai.italker.common.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description: RecyclerView 基类
 * Crete by Anding on 2019-09-19
 */
@SuppressWarnings({"unchecked", "unused"})
public abstract class BaseRecyclerAdapter<Data>
        extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder<Data>>
        implements View.OnClickListener, View.OnLongClickListener,AdapterCallback<Data> {

    private final List<Data> mDataList;
    private AdapterListener<Data> mListener;

    /**
     * 构造函数模块
     */
    public BaseRecyclerAdapter() {
        this(null);
    }

    private BaseRecyclerAdapter(AdapterListener<Data> listener) {
        this(new ArrayList<Data>(),listener);
    }

    public BaseRecyclerAdapter(List<Data> mDataList, AdapterListener<Data> listener) {
        this.mDataList = mDataList;
        this.mListener = listener;
    }

    /**
     * 复写默认的布局类型返回
     * @param position 坐标
     * @return 类型标识 其实复写后返回的都是XML的布局ID
     */
    @Override
    public int getItemViewType(int position) {
        return getItemViewType(position,mDataList.get(position));
    }

    /**
     * 得到布局的类型
     *
     * @param position 坐标位置
     * @param data 当前的数据
     * @return XML文件的ID,用于创建ViewHolder
     */
    @LayoutRes
    protected abstract int getItemViewType(int position,Data data);

    /**
     * 创建一个ViewHolder
     * @param viewGroup 父布局
     * @param i  界面的类型，约定为XML的ID
     * @return ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder<Data> onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 得到布局填充器 用于将XML初始化成View
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        // 把XML id为i的文件初始化一个root view
        View root = inflater.inflate(i,viewGroup,false);
        // 通过子类必须实现的方法，得到一个ViewHolder
        ViewHolder<Data> holder = onCreateViewHolder(root,i);

        root.setTag(R.id.tag_recycler_holder, holder);
        // 设置事件的点击
        root.setOnClickListener(this);
        root.setOnLongClickListener(this);

        //进行界面注解绑定
        holder.unbinder = ButterKnife.bind(holder,root);
        //绑定callback
        holder.callback = this;

        return holder;
    }

    /**
     * 从实现类的得到一个ViewHolder
     * @param root 根布局
     * @param viewType 布局类型，在这里我把他当成xml文件ID
     * @return viewHolder
     */
    protected abstract ViewHolder<Data> onCreateViewHolder(View root, int viewType);

    /**
     * 数据data绑定到viewHolder上
     * @param Holder viewHolder
     * @param i 位置
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder<Data> Holder, int i) {
        // 得到需要绑定的数据
        Data data = mDataList.get(i);
        // 触发Holder的绑定方法
        Holder.bind(data);
    }

    /**
     * 返回数据列表的长度
     * @return 数据长度
     */
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 返回整个数据集合
     * @return List<Data>
     */
    public List<Data> getList() {
        return mDataList;
    }

    public void add (Data data) {
        mDataList.add(data);
        notifyItemInserted(mDataList.size()-1);
    }

    /**
     * 插入一堆数据，并通知这段集合更新
     * @param dataList Data
     */
    public void add(Data... dataList) {
        if (dataList != null && dataList.length >0) {
            int startPos = mDataList.size();
            Collections.addAll(mDataList,dataList);
            notifyItemRangeInserted(startPos,dataList.length);
        }
    }

    /**
     * 插入一堆数据，并通知这段集合更新
     * @param dataList Data
     */
    public void add(Collection<Data> dataList) {
        if (dataList != null && dataList.size() >0) {
            int startPos = mDataList.size();
            mDataList.addAll(dataList);
            notifyItemRangeInserted(startPos,dataList.size());
        }
    }

    /**
     * 删除操作
     */
    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    /**
     * 替换一个新的集合 包括清空
     * @param dataList 一个新的集合
     */
    public void replace(Collection<Data> dataList) {
        mDataList.clear();
        if (dataList != null && dataList.size() > 0) {
            mDataList.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    /**
     * 更新某一个数据项
     * @param data 新的数据项
     * @param holder 老的数据项
     */
    public void update(Data data,ViewHolder<Data> holder) {
        //得到当前的ViewHolder的坐标
        int pos = holder.getAdapterPosition();
        if (pos>=0) {
            //进行数据的移除和更新
            mDataList.remove(pos);
            mDataList.add(pos,data);
            //通知这个坐标下的数据有更新
            notifyItemChanged(pos);
        }
    }

    @Override
    public void onClick(View v) {
        ViewHolder viewHolder = (ViewHolder) v.getTag(R.id.tag_recycler_holder);
        if (this.mListener != null) {
            // 得到viewHolder当前对应的适配器的坐标
            int pos = viewHolder.getAdapterPosition();
            //回调子类实现的方法
            this.mListener.onItemClick(viewHolder,mDataList.get(pos));
        }
    }

    @Override
    public boolean onLongClick(View v) {
        ViewHolder viewHolder = (ViewHolder) v.getTag(R.id.tag_recycler_holder);
        if (this.mListener != null) {
            // 得到viewHolder当前对应的适配器的坐标
            int pos = viewHolder.getAdapterPosition();
            //回调子类实现的方法
            this.mListener.onItemLongClick(viewHolder,mDataList.get(pos));
            return true;
        }
        return false;
    }

    /**
     * 自定义的ViewHolder
     * @param <Data> 泛型
     */
    protected static abstract class ViewHolder<Data> extends RecyclerView.ViewHolder {

        private Unbinder unbinder;
        private AdapterCallback<Data> callback;
        protected Data mData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        /**
         * 用于绑定数据的触发
         * @param data 绑定的数据
         */
        void bind(Data data) {
            this.mData = data;
            onBind(data);
        }

        /**
         * 当触发绑定数据的时候的回调，必须重写
         * @param data 绑定的数据
         */
        protected abstract void onBind(Data data);

        /**
         * 当触发绑定数据的时候回调，必须重写
         * @param data 绑定的数据
         */
        public void updataData(Data data) {
            if (this.callback != null) {
                this.callback.update(data,this);
            }
        }
    }

    /**
     * 给外部接口设置Listener
     * @param adapterListener AdapterListener
     */
    public void setListener(AdapterListener<Data> adapterListener) {
        this.mListener = adapterListener;
    }

    /**
     * 我们自定义的listener
     * @param <Data> 泛型
     */
    public interface AdapterListener<Data> {
        //当cell点击时触发
        void onItemClick(BaseRecyclerAdapter.ViewHolder holder,Data data);

        //当Cell长按时触发
        void onItemLongClick(BaseRecyclerAdapter.ViewHolder holder,Data data);
    }

    /**
     * 对回调接口做一次实现AdapterListener 子类继承实现即可
     *
     * @param <Data>
     */
    public static abstract class AdapterListenerImpl<Data> implements AdapterListener<Data> {
        @Override
        public void onItemClick(ViewHolder holder, Data data) {

        }

        @Override
        public void onItemLongClick(ViewHolder holder, Data data) {

        }
    }
}
