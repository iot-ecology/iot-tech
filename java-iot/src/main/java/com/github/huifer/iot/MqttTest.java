package com.github.huifer.iot;

import org.eclipse.paho.client.mqttv3.*;

import java.nio.charset.StandardCharsets;

public class MqttTest {
    public static void main(String[] args) throws MqttException {
        String broker = "tcp://127.0.0.1:1883";
        String clientId = "kjljl";
        MqttClient client = new MqttClient(broker, clientId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("admin");
        options.setPassword("admin123".toCharArray());
        client.connect(options);





        // 消息接收
        client.setCallback(new MqttCallback() {
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("topic: " + topic);
                System.out.println("qos: " + message.getQos());
                System.out.println("message content: " + new String(message.getPayload()));
            }

            public void connectionLost(Throwable cause) {
                System.out.println("connectionLost: " + cause.getMessage());
            }

            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("deliveryComplete: " + token.isComplete());
            }
        });;
        client.subscribe("t/#");


        // 消息发送
//        MqttMessage message = new MqttMessage();
//        message.setPayload("hello".getBytes(StandardCharsets.UTF_8));
//        client.publish("t/2/b", message);

    }
}
