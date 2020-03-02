package net.guikai.italker.factory.presenter.search;

import net.guikai.italker.common.presenter.BaseContract;
import net.guikai.italker.factory.model.card.GroupCard;
import net.guikai.italker.factory.model.card.UserCard;

import java.util.List;

/**
 * Description: 搜索界面逻辑契约
 * Crete by Anding on 2020-02-29
 */
public interface SearchContract {
    interface Presenter extends BaseContract.Presenter {
        // 搜索内容
        void search(String content);
    }

    interface UserView extends BaseContract.View<Presenter> {
        void onSearchDone(List<UserCard> userCards);
    }

    // 搜索群的界面
    interface GroupView extends BaseContract.View<Presenter> {
        void onSearchDone(List<GroupCard> groupCards);
    }

}
