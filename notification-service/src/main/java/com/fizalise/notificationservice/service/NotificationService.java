package com.fizalise.notificationservice.service;

import com.fizalise.orderservice.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final JavaMailSender mailSender;
    private static final String serviceEmail = "photostore@mail.ru";
    @KafkaListener(topics = "order-notification")
    public void handleTopic(OrderPlacedEvent orderPlacedEvent) {
        log.info("Получен объект: {}", orderPlacedEvent);
        MimeMessagePreparator preparedEmail = prepareEmail(orderPlacedEvent);
        try {
            mailSender.send(preparedEmail);
            log.info("Письмо успешно отправлено на почту {}", orderPlacedEvent.getEmail());
        } catch (MailException e) {
            log.error("Не удалось отправить письмо на почту {}. Причина: {}",
                    orderPlacedEvent.getEmail(), e.getMessage());
        }
    }
    private MimeMessagePreparator prepareEmail(OrderPlacedEvent orderPlacedEvent) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(serviceEmail);
            messageHelper.setTo(orderPlacedEvent.getEmail().toString());
            messageHelper.setSubject("Ваш заказ успешно оформлен");
            messageHelper.setText("""
                    Здравствуйте, %s %s
                    На ваше имя был успешно оформлен заказ с кодом %s
                                        
                    Компания PhotoStore благодарит вас за покупку!
                    """.formatted(orderPlacedEvent.getName(), orderPlacedEvent.getSurname(),
                    orderPlacedEvent.getOrderId()));
        };
    }
}
