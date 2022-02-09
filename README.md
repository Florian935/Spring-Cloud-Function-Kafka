# Spring Cloud Stream Function with Kafka

This project is a demo of how to implement Spring Cloud Function ___Producer___, ___Processor___ and ___Consumer___ with Kafka integration.

## Architecture

___Producer___ --> TOPIC 1 <-- ___Processor___ --> TOPIC 2 --> ___Consumer___

The producer publish a message in the TOPIC 1. 
The processor consume the published message AND publish a new message into the TOPIC 2.
The consumer consume the message published by the processor into the TOPIC 2.