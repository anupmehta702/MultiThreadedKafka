package com.example.springkafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:application.properties")
@Configuration
public class ApplicationProp {

    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapAddress;

    @Value("${spring.kafka.consumer.group-id}")
    public String consumerGroupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    public String autoOffsetReset;

    @Value("${spring.kafka.consumer.concurrency}")
    public Integer concurrencyLimit;

    @Value("${spring.kafka.topic.name}")
    public String topicName;

    @Value("${spring.kafka.parallel.topic.name}")
    public String topicNameForParallelConsumption;



}
