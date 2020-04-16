package net.guikai.italker.factory.data.user;

import net.guikai.italker.factory.model.card.UserCard;

/**
 * Description: 用户中心的基本定义
 * Crete by Anding on 2020-04-16
 */
public interface UserCenter {
    // 分发处理一堆用户卡片的信息，并更新到数据库
    void dispatch(UserCard... cards);
}
