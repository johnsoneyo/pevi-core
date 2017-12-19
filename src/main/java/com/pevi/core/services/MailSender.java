/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.services;

import com.pevi.core.models.dto.AnonymousOrder;
import com.pevi.core.models.dto.OrderDTO;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    public void sendMail(String from, String to, String subject, int invoiceId,AnonymousOrder order) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @SuppressWarnings({"rawtypes", "unchecked"})
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(to);
                message.setBcc(to);
                message.setFrom(from);
                message.setSubject(String.format("Pevi Invoice Order for %s", order.getFname()));
                message.setSentDate(new Date());
                Map model = new HashMap();
                model.put("orders", order.getOrders());
                model.put("ord", order);
                model.put("inv", invoiceId);
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy h:mm a");
                String format = sdf.format(new Date());
                model.put("timeCreated", format);
                List<OrderDTO> asList = Arrays.asList(order.getOrders());
                BigDecimal total = BigDecimal.ZERO;
                for(OrderDTO oed : asList){
                    total = total.add(BigDecimal.valueOf(oed.getQuantity()).multiply(oed.getPrice()));
                }
                model.put("total",total);

                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        engine, "velocity/order.vm", "UTF-8", model);
                message.setText(text, true);
            }
        };
        javaMailSender.send(preparator);

    }

}
