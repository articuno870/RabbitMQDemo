package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.demo.config.MessagingConfig;
import com.example.demo.model.OrderStatus;

@Service
public class ConsumerListner {

	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(OrderStatus orderStatus) {
		System.out.println("Message recieved from queue : " + orderStatus);
	}
}
