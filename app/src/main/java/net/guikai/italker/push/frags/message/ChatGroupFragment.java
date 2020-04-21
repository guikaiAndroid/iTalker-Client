package net.guikai.italker.push.frags.message;

import net.guikai.italker.factory.model.db.Group;
import net.guikai.italker.factory.presenter.message.ChatContract;

/**
 * Description:
 * Crete by Anding on 2020-04-20
 */
public class ChatGroupFragment extends ChatFragment<Group>{
    @Override
    protected int getHeaderLayoutId() {
        return 0;
    }

    @Override
    protected ChatContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onInit(Group group) {

    }
}
