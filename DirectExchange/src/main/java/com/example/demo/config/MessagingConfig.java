package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
	public static final String EXCHANGE = "direct-exchange";
	public static final String ROUTING_KEY = "red";
	private static final String MARKETING_QUEUE = "marketingQueue";
	private static final String FINANACE_QUEUE = "financeQueue";
	private static final String ADMIN_QUEUE = "adminQueue";

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
	public DirectExchange exchange() {
		return new DirectExchange(EXCHANGE);
	}

	@Bean
	public Binding marketingBinding(Queue marketingQueue, DirectExchange exchange) {
		return BindingBuilder.bind(marketingQueue).to(exchange).with("marketing");
	}

	@Bean
	public Binding financeBinding(Queue financeQueue, DirectExchange exchange) {
		return BindingBuilder.bind(financeQueue).to(exchange).with("finance");
	}

	@Bean
	public Binding adminBinding(Queue adminQueue, DirectExchange exchange) {
		return BindingBuilder.bind(adminQueue).to(exchange).with("admin");
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
