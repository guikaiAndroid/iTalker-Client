package net.guikai.italker.push.frags.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.guikai.italker.common.app.PresenterFragment;
import net.guikai.italker.common.widget.EmptyView;
import net.guikai.italker.common.widget.PortraitView;
import net.guikai.italker.common.widget.recycler.BaseRecyclerAdapter;
import net.guikai.italker.factory.model.db.User;
import net.guikai.italker.factory.presenter.contact.ContactContract;
import net.guikai.italker.factory.presenter.contact.ContactPresenter;
import net.guikai.italker.push.R;
import net.guikai.italker.push.activities.MessageActivity;
import net.guikai.italker.push.activities.PersonalActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Crete by Anding on 2019-11-04
 */
public class ContactFragment extends PresenterFragment<ContactContract.Presenter>
implements ContactContract.View{

    @BindView(R.id.empty)
    EmptyView mEmptyView;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    // 适配器，User，可以直接从数据库查询数据
    private BaseRecyclerAdapter<User> mAdapter;


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        // 初始化Recycler
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter = new BaseRecyclerAdapter<User>() {

            @Override
            protected int getItemViewType(int position, User user) {
                // 返回cell的布局id
                return R.layout.cell_contact_list;
            }

            @Override
            protected ViewHolder<User> onCreateViewHolder(View root, int viewType) {
                return new ContactFragment.ViewHolder(root);
            }
        });

        mAdapter.setListener(new BaseRecyclerAdapter.AdapterListenerImpl<User>() {
            @Override
            public void onItemClick(BaseRecyclerAdapter.ViewHolder holder, User user) {
                MessageActivity.show(getContext(), user);
            }
        });

        // 初始化占位布局
        mEmptyView.bind(mRecycler);
        setPlaceHolderView(mEmptyView);
    }

    @Override
    public BaseRecyclerAdapter<User> getRecyclerAdapter() {
        return mAdapter;
    }

    @Override
    public void onAdapterDataChanged() {
        // 进行界面操作
        mPlaceHolderView.triggerOkOrEmpty(mAdapter.getItemCount() > 0);
    }

    @Override
    protected void onFirstInit() {
        super.onFirstInit();
        // 进行一次数据加载
        mPresenter.start();
    }

    @Override
    protected ContactContract.Presenter initPresenter() {
        return new ContactPresenter(this);
    }

    class ViewHolder extends BaseRecyclerAdapter.ViewHolder<User> {

        @BindView(R.id.im_portrait)
        PortraitView mPortraitView;

        @BindView(R.id.txt_name)
        TextView mName;

        @BindView(R.id.txt_desc)
        TextView mDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(User user) {
            mPortraitView.setup(Glide.with(ContactFragment.this),user);
            mName.setText(user.getName());
            mDesc.setText(user.getDesc());
        }

        @OnClick(R.id.im_portrait)
        void onPortraitClick() {
            // 显示个人信息界面
            PersonalActivity.show(getContext(), mData.getId());
        }
    }
}
