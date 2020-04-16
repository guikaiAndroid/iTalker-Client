package net.guikai.italker.factory.data.message;

import net.guikai.italker.factory.model.card.MessageCard;

/**
 * Description: 消息中心，进行消息卡片的消费
 * Crete by Anding on 2020-04-16
 */
public interface MessageCenter {
    void dispatch(MessageCard... cards);
}
