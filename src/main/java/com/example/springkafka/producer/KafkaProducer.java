package com.example.springkafka.producer;

import com.example.springkafka.config.ApplicationProp;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class KafkaProducer {

    @Autowired
    ApplicationProp prop;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Integer startIndex) {
        LOGGER.info(String.format("Message sent %s", startIndex));

        try {
            Stream.iterate(startIndex, i -> i + 1)
                    .limit(10)
                    .forEach(i -> {
                                kafkaTemplate.send(new ProducerRecord(prop.topicNameForParallelConsumption, String.valueOf(i), "New message - " + i));
                            }
                    );
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}