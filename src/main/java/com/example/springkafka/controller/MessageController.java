package com.example.springkafka.controller;


import com.example.springkafka.producer.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spring-kafka/publish/")
public class MessageController {

    private KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("{startIndex}")
    public ResponseEntity<String> publish(@PathVariable Integer startIndex) {
        kafkaProducer.sendMessage(startIndex);
        return ResponseEntity.ok("Messages sent to the topic from index -->"+startIndex);
    }
}