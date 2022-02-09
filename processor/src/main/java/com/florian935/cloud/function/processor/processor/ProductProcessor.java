package com.florian935.cloud.function.processor.processor;

import com.florian935.cloud.function.processor.domain.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.function.Function;

@Configuration
public class ProductProcessor {

    @Bean
    Function<Flux<Message<Product>>, Flux<Message<Product>>> productEventProcessor() {

        return messageFlux -> messageFlux.map(message -> {

            Integer id = message.getPayload().getId();
            String nameToUppercase = message.getPayload().getName().toUpperCase();
            Product product = Product
                    .builder()
                    .id(id)
                    .name(nameToUppercase)
                    .build();

            Integer partitionIdReceived = (Integer)(Objects
                    .requireNonNull(message
                            .getHeaders()
                            .get(KafkaHeaders.RECEIVED_PARTITION_ID)))
                    ;

            return MessageBuilder
                    .withPayload(product)
                    .copyHeaders(message.getHeaders())
                    .setHeader(KafkaHeaders.PARTITION_ID, partitionIdReceived)
                    .build();
        });
    }
}
