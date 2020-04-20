package net.guikai.italker.push.frags.message;

import net.guikai.italker.factory.presenter.BaseContract;
import net.guikai.italker.push.R;

/**
 * Description:
 * Crete by Anding on 2020-04-20
 */
public class ChatUserFragment extends ChatFragment{
    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.lay_chat_header_user;
    }
}
