package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Topic;
import com.example.demo.mqtt.MQTTPublisherBase;
import com.example.demo.mqtt.MQTTSubscriber;
import com.example.demo.mqtt.MQTTSubscriberBase;

@RestController
public class MqttController {

    @Autowired
    MQTTPublisherBase publisher;

    @Autowired
    MQTTSubscriberBase subscribe;

    @PostMapping("/mqtt/send")
    public ResponseEntity<?> publish(@RequestBody Topic data) {
        try {
            publisher.publishMessage(data.getTopic(), data.getMessage());
            return ResponseEntity.ok("Send to MQTT Success");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok("fail");
        }
    }

    @PostMapping("/mqtt/get")
    public ResponseEntity<?> subcribe(@RequestBody Topic data) {
        try {
            subscribe.subscribeMessage(data.getTopic());
            return ResponseEntity.ok(MQTTSubscriber.messaged);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok("fail");
        }
    }

}
