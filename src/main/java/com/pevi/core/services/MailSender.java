/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author johnson3yo
 */
@Component("javasampleapproachMailSender")
public class MailSender {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private VelocityEngine engine;

    public void sendMail(String from, String to, String subject, String body) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @SuppressWarnings({"rawtypes", "unchecked"})
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo("johnsoneyo@gmail.com");
                message.setBcc("johnsoneyo@gmail.com");
                message.setFrom("johnsoneyo@gmail.com");
                message.setSubject("New suggested podcast");
                message.setSentDate(new Date());
                Map model = new HashMap();
                model.put("newMessage", "");

                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        engine, "velocity/order.vm", "UTF-8", model);
                message.setText(text, true);
            }
        };
        javaMailSender.send(preparator);

    }

}
