package net.guikai.italker.factory.presenter.message;

import android.support.v7.util.DiffUtil;

import net.guikai.italker.factory.data.helper.MessageHelper;
import net.guikai.italker.factory.data.message.MessageDataSource;
import net.guikai.italker.factory.model.api.message.MsgCreateModel;
import net.guikai.italker.factory.model.db.Message;
import net.guikai.italker.factory.presenter.BaseSourcePresenter;
import net.guikai.italker.factory.utils.DiffUiDataCallback;

import java.util.List;

/**
 * Description: 聊天Presenter的基础类
 * Crete by Anding on 2020-04-21
 */
public class ChatPresenter<View extends ChatContract.View>
        extends BaseSourcePresenter<Message, Message, MessageDataSource, View>
        implements ChatContract.Presenter {

    // 接收者Id，可能是群，或者人的ID
    protected String mReceiverId;
    // 区分是人还是群Id
    protected int mReceiverType;

    public ChatPresenter(MessageDataSource source, View view,
                         String receiverId, int receiverType) {
        super(source, view);
        this.mReceiverId = receiverId;
        this.mReceiverType = receiverType;
    }

    // 聊天界面的回调 更新
    @Override
    public void onDataLoaded(List<Message> messages) {
        ChatContract.View view = getView();
        if (view == null)
            return;
        // 拿到老数据
        @SuppressWarnings("unchecked")
        List<Message> old = view.getRecyclerAdapter().getItems();
        // 差异计算
        DiffUiDataCallback<Message> callback = new DiffUiDataCallback<>(old, messages);
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        // 进行界面刷新
        refreshData(result, messages);
    }

    @Override
    public void pushText(String content) {
        // 构建一个新的消息
        MsgCreateModel model = new MsgCreateModel.Builder()
                .receiver(mReceiverId, mReceiverType)
                .content(content, Message.TYPE_STR)
                .build();
        // 进行网络发送
        MessageHelper.push(model);
    }

    @Override
    public void pushAudio(String path, long time) {

    }

    @Override
    public void pushImages(String[] paths) {

    }

    @Override
    public boolean rePush(Message message) {
        return false;
    }
}
