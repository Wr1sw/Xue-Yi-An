package org.cuit.xueyian.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MailService {

    public static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMail(String mail, String subject, String content){
        HashMap<String, String> map = new HashMap<>();
        map.put("mail", mail);
        map.put("subject", subject);
        map.put("content", content);
        logger.info(mail);
        rabbitTemplate.convertAndSend("xueyian.mail.send",map);
    }

}
