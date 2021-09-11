package com.example.basicsendreceive;

import com.pivotal.rabbitmq.RabbitEndpointService;
import com.pivotal.rabbitmq.stream.ProducerStream;
import com.pivotal.rabbitmq.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.UUID;
import java.util.function.Consumer;

@Configuration
public class PublisherConfiguration {

    private static final Logger log = LoggerFactory.getLogger(PublisherConfiguration.class);
    @Autowired
    RabbitEndpointService rabbit;

    @Value("${exchange:numbers}")
    String NUMBERS;

    @Value("${count:10000}")
    int count;
    @Value("${delay:1s}")
    Duration delay;

    @Bean
    @ConditionalOnProperty(name = "role", havingValue = "publisher", matchIfMissing = false)
    public CommandLineRunner publisher(Consumer<TopologyBuilder> topology) {
        return (args) -> {
// @formatter:off

            Flux<Integer> integers = Flux
                    .range(1, Integer.MAX_VALUE)
//                    .delayElements(Duration.ofSeconds(1))
                    .doOnNext(data -> System.out.println(String.format("Sending: %s", data)));

            ProducerStream<Integer> producerStream = rabbit
                    .declareTopology(topology)
                    .createProducerStream(Integer.class)
                    .route()
                    .toExchange(NUMBERS)
                    .then();

            producerStream
                    .send(integers)
                    .doOnNext(data -> System.out.println(String.format("Sent: %s", data)))
                    .blockLast();
// @formatter:on
        };
    }

}
