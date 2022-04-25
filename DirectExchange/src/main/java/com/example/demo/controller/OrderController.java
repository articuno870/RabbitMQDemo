package com.example.demo.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/direct")
public class OrderController {

	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * http://localhost:9292/order/direct/producer?exchangeName=direct-exchange&routingKey=admin&messageData=HelloWorldJavaInUse
	 * 
	 * @param exchange
	 * @param routingKey
	 * @param messageData
	 * @return
	 */
	@GetMapping("/producer")
	public String bookOrder(@RequestParam("exchangeName") String exchange,
			@RequestParam("routingKey") String routingKey, @RequestParam("messageData") String messageData) {
		amqpTemplate.convertAndSend(exchange, routingKey, messageData);
		return "Success !!";
	}

}
