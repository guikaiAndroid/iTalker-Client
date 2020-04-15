package net.guikai.italker.factory.model.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Description: 数据库的基本信息
 * Crete by Anding on 2020-02-19
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {
    public static final String NAME = "AppDatabase";
    public static final int VERSION = 2;
}
