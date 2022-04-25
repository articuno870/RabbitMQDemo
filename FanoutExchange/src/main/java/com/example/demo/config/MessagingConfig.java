package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
	public static final String FANOUT_EXCHANGE = "fanout-exchange";
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
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(FANOUT_EXCHANGE);
	}

	/**
	 * no routing key is required
	 * @param marketingQueue
	 * @param fanoutExchange
	 * @return
	 */
	@Bean
	public Binding marketingBinding(Queue marketingQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(marketingQueue).to(fanoutExchange);
	}

	@Bean
	public Binding financeBinding(Queue financeQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(financeQueue).to(fanoutExchange);
	}

	@Bean
	public Binding adminBinding(Queue adminQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(adminQueue).to(fanoutExchange);
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
