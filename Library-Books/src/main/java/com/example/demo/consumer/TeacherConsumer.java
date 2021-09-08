package com.example.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.config.MessagingConfig;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.TeacherMongo;
import com.example.demo.repository.TeacherMongoRepository;

@Component
public class TeacherConsumer {
	
	@Autowired
	private TeacherMongoRepository teacherMongoRepository;
	
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Teacher teacher) {
        System.out.println("Message recieved from queue : " + teacher);
        TeacherMongo teacherMongo = new TeacherMongo();
        teacherMongo.setId(teacher.getId());
        teacherMongo.setName(teacher.getName());
        teacherMongo.setExp(15);
        teacherMongoRepository.save(teacherMongo);
        
    }
}
