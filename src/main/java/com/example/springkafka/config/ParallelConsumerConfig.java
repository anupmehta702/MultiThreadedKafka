package com.example.springkafka.config;

import io.confluent.parallelconsumer.ParallelConsumerOptions;
import io.confluent.parallelconsumer.ParallelStreamProcessor;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.confluent.parallelconsumer.ParallelConsumerOptions.ProcessingOrder.KEY;

@Component
public class ParallelConsumerConfig {

    @Autowired
    private ApplicationProp prop;

    private Map<String, Object> parallelConsumerConfigs() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, prop.bootstrapAddress);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, prop.consumerGroupId);
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        //configs.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, maxPolInterval);
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, prop.autoOffsetReset);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return configs;
    }


    @Bean
    public ParallelStreamProcessor<String, String> setupParallelConsumer() {

        Consumer<String, String> kafkaConsumer = new KafkaConsumer<>(parallelConsumerConfigs());

        ParallelConsumerOptions options = ParallelConsumerOptions.<String, String>builder()
                .ordering(KEY)
                .maxConcurrency(3)
                .consumer(kafkaConsumer)
                .build();

        ParallelStreamProcessor<String, String> eosStreamProcessor =
                ParallelStreamProcessor.createEosStreamProcessor(options);

        List<String> topicNameList = new ArrayList<>();
        topicNameList.add(prop.topicNameForParallelConsumption);
        eosStreamProcessor.subscribe(topicNameList);

        return eosStreamProcessor;
    }
}
