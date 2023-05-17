package com.example.springkafka.consumer;

import com.example.springkafka.config.ParallelConsumerConfig;
import io.confluent.parallelconsumer.ParallelStreamProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaParallelConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaParallelConsumer.class);

    @Autowired
    ParallelConsumerConfig parallelConsumerConfig;

    @Autowired
    GenericConsumer consumer;

    public void consume() {
        log.info("polling for messages in parallel consumer ");
        parallelConsumerConfig.setupParallelConsumer().poll(records -> {
                    records.forEach(record-> consumer.consume(record.getConsumerRecord()));
                }
        );
    }

}
/* --Output --
 --- [pool-3-thread-2]   : Consuming message  Key:602 Partition:0 Offset:380 Value:New message - 602
 --- [pool-3-thread-3]   : Consuming message  Key:603 Partition:0 Offset:381 Value:New message - 603
 --- [pool-3-thread-1]   : Consuming message  Key:607 Partition:2 Offset:341 Value:New message - 607
 --- [pool-3-thread-3]   : Consuming message  Key:601 Partition:1 Offset:353 Value:New message - 601
 --- [pool-3-thread-2]   : Consuming message  Key:608 Partition:0 Offset:383 Value:New message - 608
 --- [pool-3-thread-1]   : Consuming message  Key:605 Partition:0 Offset:382 Value:New message - 605
 --- [pool-3-thread-1]   : Consuming message  Key:604 Partition:1 Offset:354 Value:New message - 604
 --- [pool-3-thread-3]   : Consuming message  Key:609 Partition:0 Offset:384 Value:New message - 609
 --- [pool-3-thread-2]   : Consuming message  Key:606 Partition:1 Offset:355 Value:New message - 606
 --- [pool-3-thread-1]   : Consuming message  Key:610 Partition:0 Offset:385 Value:New message - 610
 */