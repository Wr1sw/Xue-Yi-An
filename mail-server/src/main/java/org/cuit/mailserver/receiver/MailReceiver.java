package org.cuit.mailserver.receiver;

import org.cuit.xueyian.model.Employee;
import org.cuit.xueyian.model.Hr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
@Component
public class MailReceiver {

    public static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MailProperties mailProperties;

    @Autowired
    TemplateEngine templateEngine;

    @RabbitListener(queues = "xueyian.mail.welcome")
    public void handler(Employee employee) {
        // 收到消息，发送邮件
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
        helper.setFrom(mailProperties.getUsername());
        helper.setTo(employee.getEmail());
        helper.setSubject("入职欢迎");
        helper.setSentDate(new Date());
        // 渲染邮件模板
        Context context = new Context();
        context.setVariable("name", employee.getName());
        context.setVariable("posName", employee.getPosition().getName());
            context.setVariable("posName", employee.getPosition().getName());
//        context.setVariable("joblevelName",employee.getjOblevel().getName());
            context.setVariable("departmentName",employee.getDepartment().getName());


            Date date = new Date();
            Calendar c1 = Calendar.getInstance();
            c1.setTime(date);int month = c1.get(Calendar.MONTH)+1;
            c1.add(Calendar.DATE, 2); // 今天过后两天
            context.setVariable("date", c1.get(Calendar.YEAR)+"年"+month+"月"+c1.get(Calendar.DATE)+"日");

            c1.add(Calendar.DATE, 5); // 今天过后七天
            context.setVariable("limitDate", c1.get(Calendar.YEAR)+"年"+month+"月"+c1.get(Calendar.DATE)+"日");


            String mail = templateEngine.process("welcome", context);
        helper.setText(mail, true);
        javaMailSender.send(msg);
        } catch (MessagingException e) {
            logger.error("邮件发送失败："+e.getMessage());
            e.printStackTrace();
        }



    }

    @RabbitListener(queues = "xueyian.mail.send")
    public void mailHandler(HashMap<String, String> map){
        // 收到消息，发送邮件
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            helper.setFrom(mailProperties.getUsername());
//            收件人
            helper.setTo(map.get("mail"));
//            主题
            helper.setSubject(map.get("subject"));
            helper.setSentDate(new Date());
            // 渲染邮件模板
            Context context = new Context();
            context.setVariable("content", map.get("content"));

            String email = templateEngine.process("mail", context);
            helper.setText(email, true);
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            logger.error("邮件发送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "xueyian.mail.sendCode")
    public void SendCode(Map<String, Object> map) {
        // 收到消息，发送邮件
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(((String) map.get("email")));
            helper.setSubject("登录验证码");
            helper.setSentDate(new Date());
            // 渲染邮件模板
            Context context = new Context();
            context.setVariable("code", ((String) map.get("code")));


            String mail = templateEngine.process("sendCode", context);
            helper.setText(mail, true);
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            logger.error("邮件发送失败：" + e.getMessage());
            e.printStackTrace();
        }

    }
}
