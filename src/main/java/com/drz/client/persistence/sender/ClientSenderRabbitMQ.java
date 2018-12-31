package com.drz.client.persistence.sender;

import com.drz.client.dto.client.ClientDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public final class ClientSenderRabbitMQ implements ClientSender {

    private RabbitTemplate template;

    private String topicExchangeName;

    private String topicName;

    private String topicDeleteName;

    public ClientSenderRabbitMQ(RabbitTemplate template, String topicExchangeName, String topicName, String topicDeleteName) {
        this.template = template;
        this.topicExchangeName = topicExchangeName;
        this.topicName = topicName;
        this.topicDeleteName = topicDeleteName;
    }

    public void sendSave(ClientDTO clientDTO) {
        send(clientDTO, topicName);
    }

    public void sendDelete(ClientDTO clientDTO) {
        send(clientDTO, topicDeleteName);
    }

    private void send(ClientDTO clientDTO, String topicName) {
        template.convertAndSend(topicExchangeName, topicName, clientDTO);
    }
}
