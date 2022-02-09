package com.springboot.practice.core.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @JmsListener(destination = "${spring.jms.queue.name}")
    public void receive(String message) {
        System.out.println("Message received:" + message);
    }
}
