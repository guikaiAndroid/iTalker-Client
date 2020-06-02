package net.guikai.italker.factory.data.helper;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.guikai.italker.factory.Factory;
import net.guikai.italker.factory.R;
import net.guikai.italker.factory.data.DataSource;
import net.guikai.italker.factory.model.api.RspModel;
import net.guikai.italker.factory.model.api.group.GroupCreateModel;
import net.guikai.italker.factory.model.card.GroupCard;
import net.guikai.italker.factory.model.db.Group;
import net.guikai.italker.factory.model.db.Group_Table;
import net.guikai.italker.factory.model.db.User;
import net.guikai.italker.factory.net.Network;
import net.guikai.italker.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
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

    // 群的创建
    public static void create(GroupCreateModel model, final DataSource.CallBack<GroupCard> callback) {
        RemoteService service = Network.remote();
        service.groupCreate(model)
                .enqueue(new Callback<RspModel<GroupCard>>() {
                    @Override
                    public void onResponse(Call<RspModel<GroupCard>> call, Response<RspModel<GroupCard>> response) {
                        RspModel<GroupCard> rspModel = response.body();
                        if (rspModel.success()) {
                            GroupCard groupCard = rspModel.getResult();
                            // 唤起进行保存的操作
                            Factory.getGroupCenter().dispatch(groupCard);
                            // 返回数据
                            callback.onDataLoaded(groupCard);
                        } else {
                            Factory.decodeRspCode(rspModel, callback);
                        }
                    }

                    @Override
                    public void onFailure(Call<RspModel<GroupCard>> call, Throwable t) {
                        callback.onDataNotAvailable(R.string.data_network_error);
                    }
                });
    }

}
