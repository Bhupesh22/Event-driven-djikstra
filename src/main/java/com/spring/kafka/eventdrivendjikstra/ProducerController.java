package com.spring.kafka.eventdrivendjikstra;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/hello")
    public String hello() {
        String message = "Hello, World!";
        producerService.sendMessage(message);
        return "Message sent to Kafka: " + message;
    }
}