package net.guikai.italker.factory.data.helper;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.guikai.italker.factory.model.db.Session;
import net.guikai.italker.factory.model.db.Session_Table;

/**
 * Description: 会话辅助工具类
 * Crete by Anding on 2020-04-16
 */
public class SessionHelper {
    // 从本地查询Session
    public static Session findFromLocal(String id) {
        return SQLite.select()
                .from(Session.class)
                .where(Session_Table.id.eq(id))
                .querySingle();
    }
}
