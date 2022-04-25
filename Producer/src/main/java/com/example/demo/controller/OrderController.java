package com.example.demo.controller;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.MessagingConfig;
import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private RabbitTemplate template;

	@GetMapping("/check")
	public void a() {
		System.out.println("aaa");
	}

	@PostMapping("/{restaurantName}")
	public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
		order.setOrderId(UUID.randomUUID().toString());
		// restaurantservice
		// payment service
		OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed succesfully in " + restaurantName);
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
		return "Success !!";
	}

}
