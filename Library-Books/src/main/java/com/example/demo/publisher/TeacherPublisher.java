package com.example.demo.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.config.MessagingConfig;
import com.example.demo.entity.Teacher;


public class TeacherPublisher {
    @Autowired
    private static RabbitTemplate template;
    
	public static void publish(Teacher teacher) {
		System.out.println("Inside publisher"+teacher);
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, teacher);
		
	}
}
