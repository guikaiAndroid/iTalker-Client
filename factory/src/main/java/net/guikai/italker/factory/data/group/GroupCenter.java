package net.guikai.italker.factory.data.group;

import net.guikai.italker.factory.model.card.GroupCard;
import net.guikai.italker.factory.model.card.GroupMemberCard;

/**
 * Description: 群中心的接口定义
 * Crete by Anding on 2020-04-16
 */
public interface GroupCenter {
    // 群卡片的处理
    void dispatch(GroupCard... cards);

    // 群成员的处理
    void dispatch(GroupMemberCard... cards);
}
