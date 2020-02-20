package net.guikai.italker.push.frags.user;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import net.guikai.italker.common.app.PresenterFragment;
import net.guikai.italker.common.widget.PortraitView;
import net.guikai.italker.factory.presenter.user.UpdateInfoContract;
import net.guikai.italker.factory.presenter.user.UpdateInfoPresenter;
import net.guikai.italker.push.R;
import net.guikai.italker.push.frags.media.GalleryFragment;
import net.qiujuer.genius.ui.widget.Loading;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description: 用户更新信息的界面
 * Crete by Anding on 2020-02-20
 */
public class UpdateInfoFragment extends PresenterFragment<UpdateInfoContract.Presenter>
        implements UpdateInfoContract.View {

    @BindView(R.id.im_sex)
    ImageView mSex;

    @BindView(R.id.edit_desc)
    EditText mDesc;

    @BindView(R.id.im_portrait)
    PortraitView mPortrait;

    @BindView(R.id.loading)
    Loading mLoading;

    @BindView(R.id.btn_submit)
    Button mSubmit;

    @Override
    protected UpdateInfoContract.Presenter initPresenter() {
        return new UpdateInfoPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_update_info;
    }

    @OnClick(R.id.im_portrait)
    void onPortraitClick() {
        new GalleryFragment()
                .setListener(new GalleryFragment.OnSelectedListener() {
                    @Override
                    public void onSelectedImage(String path) {

                    }
                })
                .show(getChildFragmentManager(),GalleryFragment.class.getName());
    }

    @Override
    public void updateSucceed() {

    }
}
