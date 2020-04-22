package net.guikai.italker.factory.presenter.message;

import android.support.v7.util.DiffUtil;

import net.guikai.italker.factory.data.message.SessionDataSource;
import net.guikai.italker.factory.data.message.SessionRepository;
import net.guikai.italker.factory.model.db.Session;
import net.guikai.italker.factory.presenter.BaseSourcePresenter;
import net.guikai.italker.factory.utils.DiffUiDataCallback;

import java.util.List;

/**
 * Description: 最近聊天列表的Presenter
 * Crete by Anding on 2020-04-22
 */
public class SessionPresenter extends BaseSourcePresenter<Session,Session,
        SessionDataSource,SessionContract.View> implements SessionContract.Presenter {

    public SessionPresenter(SessionContract.View view) {
        super(new SessionRepository(), view);
    }

    @Override
    public void onDataLoaded(List<Session> sessions) {
        SessionContract.View view = getView();
        if (view == null)
            return;

        // 差异对比
        List<Session> old = view.getRecyclerAdapter().getItems();
        DiffUiDataCallback<Session> callback = new DiffUiDataCallback<>(old, sessions);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        // 刷新界面
        refreshData(result, sessions);
    }
}
