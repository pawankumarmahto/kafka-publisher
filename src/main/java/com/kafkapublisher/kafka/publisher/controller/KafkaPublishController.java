package com.kafkapublisher.kafka.publisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafkapublisher.kafka.publisher.entity.User;

@RestController
@RequestMapping("/publish")
public class KafkaPublishController {
	
	@Autowired
	private KafkaTemplate<String, User> template;
	
	@Autowired
	private KafkaTemplate<String, String> stringTemplate;
	
	private static final String  STRING_TOPIC ="STRING_TOPIC";
	private static final String  OBJECT_TOPIC ="OBJECT_TOPIC";

	
	@GetMapping("/{name}")
	public String publishMessage(@PathVariable String name) {
		
		stringTemplate.send(STRING_TOPIC, "Hi "+ name);
		return "Message published successfully";
	}
	
	@GetMapping("/publishUser")
	public String publishUserDetails() {
		User user = new User(10,"Pawan","Ranchi");
		template.send(OBJECT_TOPIC, user);
		return "User Details published successfully";
	}

}
