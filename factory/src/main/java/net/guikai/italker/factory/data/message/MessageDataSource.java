package net.guikai.italker.factory.data.message;

import net.guikai.italker.factory.data.DbDataSource;
import net.guikai.italker.factory.model.db.Message;

/**
 * Description: 消息的数据源定义，他的实现是：MessageRepository, MessageGroupRepository
 * 关注的对象是Message表
 * Crete by Anding on 2020-04-21
 */
public interface MessageDataSource extends DbDataSource<Message> {
}
