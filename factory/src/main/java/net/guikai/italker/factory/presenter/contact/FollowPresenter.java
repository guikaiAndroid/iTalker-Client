package net.guikai.italker.factory.presenter.contact;

import net.guikai.italker.factory.presenter.BasePresenter;
import net.guikai.italker.factory.data.DataSource;
import net.guikai.italker.factory.data.helper.UserHelper;
import net.guikai.italker.factory.model.card.UserCard;
import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * Description: 关注的逻辑实现
 * Crete by Anding on 2020-03-02
 */
public class FollowPresenter extends BasePresenter<FollowContract.View>
        implements FollowContract.Presenter , DataSource.CallBack<UserCard> {

    public FollowPresenter(FollowContract.View view) {
        super(view);
    }

    @Override
    public void follow(String id) {
        start();
        UserHelper.follow(id, this);
    }

    @Override
    public void onDataLoaded(UserCard userCard) {
        // 成功
        final FollowContract.View view = getView();
        if (view != null) {
            Run.onUiAsync(new Action() {
                @Override
                public void call() {
                    view.onFollowSucceed(userCard);
                }
            });
        }
    }

    @Override
    public void onDataNotAvailable(int strRes) {
        final FollowContract.View view = getView();
        if (view != null) {
            Run.onUiAsync(new Action() {
                @Override
                public void call() {
                    view.showError(strRes);
                }
            });
        }
    }
}
