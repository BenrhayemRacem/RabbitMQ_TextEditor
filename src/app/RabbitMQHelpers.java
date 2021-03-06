package app;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


import com.rabbitmq.client.*;

public class RabbitMQHelpers {
    private static Channel channel;
    public static Channel getChannel() throws IOException, TimeoutException {
        if (channel == null) {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
        }
        return channel;
    }
    /**
     * send message to queue
     */
    public static void sendMessage(String message, String exchangeName) {
        try {
            channel = getChannel();
            channel.exchangeDeclare(exchangeName, "fanout");
            channel.basicPublish(exchangeName, "", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * read message from queue queueName and feed to callback
     */
    public static void
    readMessage(String queueName,
                java.util.function.Consumer<String> callback) {
        try {
            channel = getChannel();
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                callback.accept(message);
            };
            channel.basicConsume(queueName, true, deliverCallback,
                                 consumerTag -> {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getExchangeName(int paragraph_idx) {
        return "exchange_" + paragraph_idx;
    }

    public static String createQueue(String exchangeName) {
        try {
            channel = getChannel();
            channel.exchangeDeclare(exchangeName, "fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, exchangeName, "");
            return queueName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}