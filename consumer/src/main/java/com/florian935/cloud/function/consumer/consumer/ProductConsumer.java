package com.florian935.cloud.function.consumer.consumer;

import com.florian935.cloud.function.consumer.domain.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Configuration
public class ProductConsumer {

    @Bean
    public Consumer<Flux<Message<Product>>> productEventConsumer() {

        return message -> message.doOnNext(m -> {
            System.out.println("========");
            System.out.println("    PAYLOAD: " + m.getPayload());
            System.out.println("========");
            System.out.println("    HEADERS: " + m.getHeaders());
        }).subscribe();
    }

    @Bean
    public Consumer<Flux<Message<Product>>> logEventConsumer() {

        return message -> message.doOnNext(m -> {
            System.out.println("======== An event is received: " + m);
        }).subscribe();
    }
}
