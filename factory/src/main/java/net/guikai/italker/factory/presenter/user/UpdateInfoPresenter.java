package net.guikai.italker.factory.presenter.user;

import android.text.TextUtils;

import net.guikai.italker.common.presenter.BasePresenter;
import net.guikai.italker.factory.Factory;
import net.guikai.italker.factory.R;
import net.guikai.italker.factory.data.DataSource;
import net.guikai.italker.factory.data.helper.UserHelper;
import net.guikai.italker.factory.model.api.user.UserUpdateModel;
import net.guikai.italker.factory.model.card.UserCard;
import net.guikai.italker.factory.model.db.User;
import net.guikai.italker.factory.net.UploadHelper;
import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * Description: 更新用户信息逻辑实现
 * Crete by Anding on 2020-02-20
 */
public class UpdateInfoPresenter extends BasePresenter<UpdateInfoContract.View>
        implements UpdateInfoContract.Presenter , DataSource.CallBack<UserCard> {

    final UpdateInfoContract.View view = getView();

    public UpdateInfoPresenter(UpdateInfoContract.View view) {
        super(view);
    }

    @Override
    public void update(String photoFilePath, String desc, boolean isMan) {
        start();

        if (TextUtils.isEmpty(photoFilePath) || TextUtils.isEmpty(desc)) {
            view.showError(R.string.data_account_update_invalid_parameter);
        } else {
            Factory.runOnAsync(new Runnable() {
                @Override
                public void run() {
                    String url = UploadHelper.uploadPortrait(photoFilePath);
                    if (TextUtils.isEmpty(url)) {
                        Run.onUiAsync(new Action() {
                            @Override
                            public void call() {
                                // 上传失败
                                view.showError(R.string.data_upload_error);
                            }
                        });
                    } else {
                        // 构建Model
                        UserUpdateModel model = new UserUpdateModel("", url, desc,
                                isMan ? User.SEX_MAN : User.SEX_WOMAN);
                        // 进行网络请求，上传
                        UserHelper.update(model, UpdateInfoPresenter.this);
                    }
                }
            });
        }
    }


    @Override
    public void onDataLoaded(UserCard userCard) {
        if (view == null)
            return;
        // 强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.updateSucceed();
            }
        });
    }

    @Override
    public void onDataNotAvailable(int strRes) {
        if (view == null)
            return;
        // 强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.showError(strRes);
            }
        });
    }
}
