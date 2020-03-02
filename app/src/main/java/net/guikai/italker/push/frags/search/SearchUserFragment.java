package net.guikai.italker.push.frags.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.guikai.italker.common.app.PresenterFragment;
import net.guikai.italker.common.widget.EmptyView;
import net.guikai.italker.common.widget.PortraitView;
import net.guikai.italker.common.widget.recycler.BaseRecyclerAdapter;
import net.guikai.italker.factory.model.card.UserCard;
import net.guikai.italker.factory.presenter.search.SearchContract;
import net.guikai.italker.factory.presenter.search.SearchUserPresenter;
import net.guikai.italker.push.R;
import net.guikai.italker.push.activities.SearchActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description: 搜索人的界面实现
 * Crete by Anding on 2020-02-29
 */
public class SearchUserFragment extends PresenterFragment<SearchContract.Presenter>
        implements SearchActivity.SearchFragment,SearchContract.UserView{

    @BindView(R.id.empty)
    EmptyView mEmptyView;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private BaseRecyclerAdapter<UserCard> mAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search_user;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        // 初始化Recycler
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter = new BaseRecyclerAdapter<UserCard>() {

            @Override
            protected int getItemViewType(int position, UserCard userCard) {
                // 返回cell的布局id
                return R.layout.cell_search_list;
            }

            @Override
            protected ViewHolder<UserCard> onCreateViewHolder(View root, int viewType) {
                return new SearchUserFragment.ViewHolder(root);
            }
        });

        // 初始化占位布局
        mEmptyView.bind(mRecycler);
        setPlaceHolderView(mEmptyView);
    }

    @Override
    protected void initData() {
        super.initData();
        // 发起首次搜索
        search("");
    }

    @Override
    public void search(String content) {
        mPresenter.search(content);
    }

    @Override
    public void onSearchDone(List<UserCard> userCards) {
        // 数据成功的情况下返回数据
        mAdapter.replace(userCards);
        // 如果有数据，则是OK，没有数据就显示空布局
        mPlaceHolderView.triggerOkOrEmpty(mAdapter.getItemCount() > 0);
    }

    @Override
    protected SearchContract.Presenter initPresenter() {
        return new SearchUserPresenter(this);
    }

    /**
     * 每一个Cell的布局操作
     */
    class ViewHolder extends BaseRecyclerAdapter.ViewHolder<UserCard> {

        @BindView(R.id.im_portrait)
        PortraitView mPortraitView;

        @BindView(R.id.txt_name)
        TextView mName;

        @BindView(R.id.im_follow)
        ImageView mFollow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(UserCard userCard) {
            Glide.with(SearchUserFragment.this)
                    .load(userCard.getPortrait())
                    .centerCrop()
                    .into(mPortraitView);
            mName.setText(userCard.getName());
            mFollow.setEnabled(!userCard.isFollow());
        }

        @OnClick(R.id.im_portrait)
        void onPortraitClick() {

        }

        @OnClick(R.id.im_follow)
        void onFollowClick() {
            // 发起关注
        }
    }
}
