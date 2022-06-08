package com.easywecom.wecom.strategy;

import com.easywecom.wecom.domain.WeMessagePush;

public interface Strategy {

    /**
     * 推送消息
     *
     * @param weMessagePush 消息发送的
     */
    void sendMessage(WeMessagePush weMessagePush, String corpId);


}
