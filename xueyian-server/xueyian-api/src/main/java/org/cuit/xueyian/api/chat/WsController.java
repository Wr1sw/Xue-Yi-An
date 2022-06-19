package org.cuit.xueyian.api.chat;

import org.cuit.xueyian.model.ChatMessage;
import org.cuit.xueyian.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Date;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description 消息处理类
 */
@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMessage chatMessage) {
        Hr hr = (Hr) authentication.getPrincipal();
        chatMessage.setFrom(hr.getUsername());
        // 中文名
        chatMessage.setFromNickName(hr.getName());
        chatMessage.setDate(new Date());
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getTo(), "/queue/chat", chatMessage);

    }
}
