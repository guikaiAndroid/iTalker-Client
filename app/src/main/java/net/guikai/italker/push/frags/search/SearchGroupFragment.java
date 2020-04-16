package net.guikai.italker.push.frags.search;

import net.guikai.italker.common.app.PresenterFragment;
import net.guikai.italker.factory.presenter.BaseContract;
import net.guikai.italker.push.R;
import net.guikai.italker.push.activities.SearchActivity;

/**
 * Description:
 * Crete by Anding on 2020-02-29
 */
public class SearchGroupFragment extends PresenterFragment
        implements SearchActivity.SearchFragment{
    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void search(String content) {

    }
}
