package com.example.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerListner {

	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(OrderStatus orderStatus) {
		System.out.println("Message recieved from queue : " + orderStatus);
	}
}
