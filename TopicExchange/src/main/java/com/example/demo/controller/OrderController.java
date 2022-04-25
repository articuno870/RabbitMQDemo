package com.example.demo.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * In Topic exchange
 * we are sending rounting key as queue.admin then message will go to both
 * admin queue and all queue (because all queue routing key is wildcard admin.*)
 * @author abhis
 *
 */
@RestController
@RequestMapping("/order/topic")
public class OrderController {

	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * Request for marketing queue
	 * http://localhost:9292/order/topic/producer?exchangeName=topic-exchange
	 * &routingKey=queue.marketing&messageData=MyMessageForMarketing
	 * 
	 * Request for admin queue
	 * http://localhost:9292/order/topic/producer?exchangeName=topic-exchange
	 * &routingKey=queue.admin&messageData=MyMessage
	 * 
	 * Request for finance queue
	 * http://localhost:9292/order/topic/producer?exchangeName=topic-exchange
	 * &routingKey=queue.finance&messageData=MyMessageForFinanceQueue
	 */
	@GetMapping("/producer")
	public String bookOrder(@RequestParam("exchangeName") String exchange,
			@RequestParam("routingKey") String routingKey, @RequestParam("messageData") String messageData) {
		amqpTemplate.convertAndSend(exchange, routingKey, messageData);
		return "Success !!";
	}

}
