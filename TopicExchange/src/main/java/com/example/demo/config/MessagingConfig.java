package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
	public static final String TOPIC_EXCHANGE = "topic-exchange";
	public static final String ROUTING_KEY = "red";
	private static final String MARKETING_QUEUE = "marketingQueue";
	private static final String FINANACE_QUEUE = "financeQueue";
	private static final String ADMIN_QUEUE = "adminQueue";
	private static final String ALL_QUEUE = "allQueue";

	@Bean
	public Queue marketingQueue() {
		return new Queue(MARKETING_QUEUE);
	}

	@Bean
	public Queue financeQueue() {
		return new Queue(FINANACE_QUEUE);
	}

	@Bean
	Queue adminQueue() {
		return new Queue(ADMIN_QUEUE, false);
	}

	@Bean
	Queue allQueue() {
		return new Queue(ALL_QUEUE, false);
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPIC_EXCHANGE);
	}

	@Bean
	public Binding marketingBinding(Queue marketingQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(marketingQueue).to(topicExchange).with("queue.marketing");
	}

	@Bean
	public Binding financeBinding(Queue financeQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(financeQueue).to(topicExchange).with("queue.finance");
	}

	@Bean
	public Binding adminBinding(Queue adminQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(adminQueue).to(topicExchange).with("queue.admin");
	}

	@Bean
	Binding allBinding(Queue allQueue, TopicExchange topicExchange) {
		return BindingBuilder.bind(allQueue).to(topicExchange).with("queue.*");
	}

	/**
	 * below code is used when we have to convert java object to json object
	 */
	/*
	 * @Bean public MessageConverter converter() { return new
	 * Jackson2JsonMessageConverter(); }
	 * 
	 * @Bean public AmqpTemplate template(ConnectionFactory connectionFactory) {
	 * final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	 * rabbitTemplate.setMessageConverter(converter()); return rabbitTemplate; }
	 */

}
