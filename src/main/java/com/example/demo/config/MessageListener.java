package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.mqtt.MQTTSubscriberBase;

@Component
public class MessageListener implements Runnable {

    @Autowired
    MQTTSubscriberBase subscriber;

    @Override
    public void run() {
        while (true) {
            subscriber.subscribeMessage("demo");
        }
    }

}
