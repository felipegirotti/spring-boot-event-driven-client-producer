package com.drz.client.config;

import com.drz.client.persistence.sender.ClientSender;
import com.drz.client.persistence.sender.ClientSenderRabbitMQ;
import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "client.sender")
@Data
public class ClientConfig {

    private String topicExchangeName;

    private String queueName;

    private String topicName;

    private String topicDeleteName;

    @Bean
    Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding bindingSave(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(topicName);
    }

    @Bean
    Binding bindingDelete(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(topicDeleteName);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        return rabbitTemplate;
    }

    @Bean
    ClientSender clientSender(RabbitTemplate rabbitTemplate) {
        return new ClientSenderRabbitMQ(rabbitTemplate, topicExchangeName, topicName, topicDeleteName);
    }
}
