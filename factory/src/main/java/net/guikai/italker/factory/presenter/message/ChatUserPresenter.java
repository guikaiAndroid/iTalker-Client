package net.guikai.italker.factory.presenter.message;

import net.guikai.italker.factory.data.helper.UserHelper;
import net.guikai.italker.factory.data.message.MessageRepository;
import net.guikai.italker.factory.model.db.Message;
import net.guikai.italker.factory.model.db.User;

/**
 * Description: 单聊逻辑实现
 * Crete by Anding on 2020-04-21
 */
public class ChatUserPresenter extends ChatPresenter<ChatContract.UserView>
        implements ChatContract.Presenter {

    public ChatUserPresenter(ChatContract.UserView view, String receiverId) {
        // 数据源，View，接收者，接收者的类型
        super(new MessageRepository(receiverId), view, receiverId, Message.RECEIVER_TYPE_NONE);


    }

    @Override
    public void start() {
        super.start();

        // 从本地拿这个人的信息
        User receiver = UserHelper.findFromLocal(mReceiverId);
        getView().onInit(receiver);
    }
}
