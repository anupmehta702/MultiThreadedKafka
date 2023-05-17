package com.example.springkafka;

import com.example.springkafka.consumer.KafkaParallelConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SpringKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaApplication.class, args);
        ConfigurableApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(SpringKafkaApplication.class);
        KafkaParallelConsumer kafkaParallelConsumer = applicationContext.getBean(KafkaParallelConsumer.class);
        kafkaParallelConsumer.consume();
    }

}
