package com.example.springkafka.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<?, ?> record) throws InterruptedException {
        if(record.partition() == 0){
            LOGGER.info("Deliberately pausing for partition 0 with message value -->",record.value());
            Thread.sleep(5000);
        }
        LOGGER.info(String.format("Message processed :: Topic:%s Partition:%s Offset:%s Key:%s Value:%s"
                , record.topic(), record.partition(), record.offset(), record.key(), record.value()));
    }
}
