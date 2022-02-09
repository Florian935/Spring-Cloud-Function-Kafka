package com.florian935.cloud.function.producer.controller;

import com.florian935.cloud.function.producer.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Sinks;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequestMapping("/api/v1.0/products")
public class ProductController {

    Sinks.Many<Message<Product>> productSinks;

    @GetMapping(value = "/publish/{id}/{name}/{action}", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(OK)
    Product publish(@PathVariable Integer id, @PathVariable String name, @PathVariable String action) {

        final Product product = Product.builder()
                .id(id)
                .name(name)
                .build();

        final Integer partitionId = action.equals("create") ? 3 : 4;

        final Message<Product> message = MessageBuilder
                .withPayload(product)
                // by setting PARTITION_ID header, Kafka Binders know in which partition he has to write
                .setHeader(KafkaHeaders.PARTITION_ID, partitionId)
                .setHeader("key", action)
                .build();

        productSinks.tryEmitNext(message);

        return product;
    }
}
