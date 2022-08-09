package com.example.demo.mqtt;

public interface MQTTPublisherBase {

    /**
     * Publish message
     * 
     * @param topic
     * @param jasonMessage
     */
    public void publishMessage(String topic, String message);

    /**
     * Disconnect MQTT Client
     */
    public void disconnect();

}
