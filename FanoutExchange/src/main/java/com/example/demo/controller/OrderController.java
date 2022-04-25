package com.example.demo.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * message will be sent to all queue
 * 
 * @author abhis
 *
 */
@RestController
@RequestMapping("/order/fanout")
public class OrderController {

	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * http://localhost:9292/order/fanout/producer?exchangeName=fanout-exchange
	 * &messageData=MyMessageForFanoutApplication in fanout no routing key is
	 * required
	 */
	@GetMapping("/producer")
	public String bookOrder(@RequestParam("exchangeName") String exchange,
			@RequestParam("messageData") String messageData) {
		amqpTemplate.convertAndSend(exchange, "", messageData);
		return "Success !!";
	}

}
