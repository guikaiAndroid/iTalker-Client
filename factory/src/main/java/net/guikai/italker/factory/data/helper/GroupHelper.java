package net.guikai.italker.factory.data.helper;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.guikai.italker.factory.Factory;
import net.guikai.italker.factory.model.api.RspModel;
import net.guikai.italker.factory.model.card.GroupCard;
import net.guikai.italker.factory.model.db.Group;
import net.guikai.italker.factory.model.db.Group_Table;
import net.guikai.italker.factory.model.db.User;
import net.guikai.italker.factory.net.Network;
import net.guikai.italker.factory.net.RemoteService;

import retrofit2.Response;

/**
 * Description: 对群的一个简单的辅助工具类
 * Crete by Anding on 2020-04-16
 */
public class GroupHelper {

    public static Group find(String groupId) {
        Group group = findFromLocal(groupId);
        if (group == null)
            group = findFormNet(groupId);
        return group;
    }

    // 从本地找Group
    public static Group findFromLocal(String groupId) {
        return SQLite.select()
                .from(Group.class)
                .where(Group_Table.id.eq(groupId))
                .querySingle();
    }

    // 从网络找Group
    public static Group findFormNet(String id) {
        // TODO 网络找Group
        return null;
    }


}
