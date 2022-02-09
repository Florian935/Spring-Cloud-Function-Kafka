package com.florian935.cloud.function.producer.configuration;

import com.florian935.cloud.function.producer.domain.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Sinks;

@Configuration
public class ProducerConfig {


    @Bean
    public Sinks.Many<Message<Product>> productSinks() {

        return Sinks.many().multicast().onBackpressureBuffer();
    }
}
