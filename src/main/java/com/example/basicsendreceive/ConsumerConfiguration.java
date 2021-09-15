/*
package com.example.basicsendreceive;

import com.pivotal.rabbitmq.RabbitEndpointService;
import com.pivotal.rabbitmq.ReactiveRabbit;
import com.pivotal.rabbitmq.stream.ConsumerStream;
import com.pivotal.rabbitmq.stream.Transaction;
import com.pivotal.rabbitmq.stream.TransactionalConsumerStream;
import com.pivotal.rabbitmq.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessResourceFailureException;
import reactor.core.Disposable;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

@Configuration
public class ConsumerConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ConsumerConfiguration.class);
    @Autowired
    RabbitEndpointService rabbit;
    @Value("${queue:numbers}")
    String consumerQueue;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Consumer<TopologyBuilder> topology;


    */
/*@Bean
    public Disposable consumer() {

        return rabbit
                .declareTopology(topology)
                .createConsumerStream(consumerQueue, Integer.class)
                .receive()
                .doOnNext(number -> log.info("Received: {}", number))
                .subscribe();
    }*//*

}
*/
