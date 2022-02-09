package com.florian935.cloud.function.producer.producer;

import com.florian935.cloud.function.producer.domain.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class ProductProducer {

    @Bean
    public Supplier<Flux<Message<Product>>> productEventProducer(Sinks.Many<Message<Product>> productSinks) {
        return productSinks::asFlux;
    }
}
