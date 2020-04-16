package net.guikai.italker.factory.data.helper;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.guikai.italker.factory.model.db.Message;
import net.guikai.italker.factory.model.db.Message_Table;

/**
 * Description: 消息工具类
 * Crete by Anding on 2020-04-16
 */
public class MessageHelper {
    // 从本地找消息
    public static Message findFromLocal(String id) {
        return SQLite.select()
                .from(Message.class)
                .where(Message_Table.id.eq(id))
                .querySingle();
    }

}
