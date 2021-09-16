package com.example.basicsendreceive;

import com.pivotal.rabbitmq.RabbitEndpointService;
import com.pivotal.rabbitmq.stream.ProducerStream;
import com.pivotal.rabbitmq.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Consumer;

@Configuration
public class PublisherConfiguration {

    private static final Logger log = LoggerFactory.getLogger(PublisherConfiguration.class);
    private final RabbitEndpointService rabbit;
    private final Consumer<TopologyBuilder> topology;
    @Value("${queue:emails}")
    String queueName;


    public PublisherConfiguration(RabbitEndpointService rabbit, Consumer<TopologyBuilder> topology) {
        this.rabbit = rabbit;
        this.topology = topology;
    }

    @Bean
    public Disposable publisher() {

        Flux<String> integers = Flux
                .range(1, 2)
                .map(val -> "oitejjho" + val + "@gmail.com");
//                .doOnNext(data -> System.out.println(String.format("Sending: %s", data)));

        ProducerStream<String> producerStream = rabbit
                .declareTopology(topology)
                .createProducerStream(String.class)
                .route()
                .toExchange(queueName)
                .then();

        return producerStream
                .send(integers)
                .delayElements(Duration.ofMillis(5))
                .doOnNext(data -> System.out.println(String.format("Sent: %s", data)))
                .subscribe();



        /*Flux<Integer> integers = Flux
                .range(1, 10)
                .doOnNext(data -> System.out.println(String.format("Sending: %s", data)));

        ProducerStream<Integer> producerStream = rabbit
                .declareTopology(topology)
                .createProducerStream(Integer.class)
                .route()
                .toExchange(queueName)
                .then();

        return producerStream
                .send(integers)
                .delayElements(Duration.ofMillis(5))
                .doOnNext(data -> System.out.println(String.format("Sent: %s", data)))
                .subscribe();*/
    }

}
