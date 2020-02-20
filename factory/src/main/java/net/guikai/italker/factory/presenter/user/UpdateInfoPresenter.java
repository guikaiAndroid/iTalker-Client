package net.guikai.italker.factory.presenter.user;

import net.guikai.italker.common.presenter.BasePresenter;

/**
 * Description: 更新用户信息逻辑实现
 * Crete by Anding on 2020-02-20
 */
public class UpdateInfoPresenter extends BasePresenter<UpdateInfoContract.View>
        implements UpdateInfoContract.Presenter {

    public UpdateInfoPresenter(UpdateInfoContract.View view) {
        super(view);
    }

    @Override
    public void update(String photoFilePath, String desc, boolean isMan) {

    }
}
