package com.example.demo.listner;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.model.Order;

@Component
public class RabbitMQListner {

	@RabbitListener(queues = "${user.finance.queue}")
	public void messageListner(Order order) {
		System.out.println(order);
	}
	
	@RabbitListener(queues = "${user.admin.queue}")
	public void adminListner(String message) {
		System.out.println(message);
	}

}
