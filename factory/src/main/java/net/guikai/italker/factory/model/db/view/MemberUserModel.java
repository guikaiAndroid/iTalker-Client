package net.guikai.italker.factory.model.db.view;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.QueryModel;

import net.guikai.italker.factory.model.db.AppDatabase;

/**
 * Description: 群成员对应的用户的简单信息表
 * Crete by Anding on 2020-04-20
 */
@QueryModel(database = AppDatabase.class)
public class MemberUserModel {
    @Column
    public String userId; // User-id/Member-userId
    @Column
    public String name; // User-name
    @Column
    public String alias; // Member-alias
    @Column
    public String portrait; // User-portrait
}
