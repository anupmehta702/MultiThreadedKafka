package com.example.springkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GenericConsumer {
    private static final Logger log = LoggerFactory.getLogger(GenericConsumer.class);


    public void consume(ConsumerRecord<String,String> record){
        log.info(String.format("Consuming message  Key:%s Partition:%s Offset:%s Value:%s"
                ,record.key(),record.partition(), record.offset(),record.value()));

    }
}
