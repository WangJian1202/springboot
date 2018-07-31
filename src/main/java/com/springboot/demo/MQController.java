package com.springboot.demo;

import com.springboot.mq.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MQController {
    @Autowired
    private Producer producer;
    @RequestMapping("/mq")
    public String login(){
       javax.jms.Destination destination=new ActiveMQQueue("Queue.front_experiment_online");
        for(int i=0; i<100; i++){
            producer.sendMessage(destination, "myname is chhliu!!!");
        }
        return "login";
    }
}
