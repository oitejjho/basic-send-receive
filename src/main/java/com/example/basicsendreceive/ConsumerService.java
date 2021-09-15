/*
package com.example.basicsendreceive;


import com.pivotal.rabbitmq.RabbitEndpointService;
import com.pivotal.rabbitmq.ReactiveRabbit;
import com.pivotal.rabbitmq.stream.TransactionalConsumerStream;
import com.pivotal.rabbitmq.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.Exceptions;

import java.util.UUID;
import java.util.function.Consumer;

@Service
public class ConsumerService {

    private static final Logger log = LoggerFactory.getLogger(ConsumerConfiguration.class);
    @Autowired
    RabbitEndpointService rabbit;
    @Value("${queue:numbers}")
    String consumerQueue;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Consumer<TopologyBuilder> topology;

    public void consume(){
        TransactionalConsumerStream<Integer> consumerStream = rabbit
                .declareTopology(topology)
                .createTransactionalConsumerStream(consumerQueue, Integer.class);

        consumerStream
                .receive()
//                    .delayElements(Duration.ofMillis(3))
                .doOnNext(number -> {
                    log.info("Received: {}", number);
                    UserEntity userEntity = new UserEntity();
                    userEntity.setCardId(UUID.randomUUID().toString());
                    userEntity.setFirstName("oitejjho");
                    userEntity.setSecondName("dutta");
                    userEntity.setType("NOTHING");
                    userEntity.setStatus(1);
                    userEntity.setLevel(1);
                    userEntity.setDateOfBirth("27-11-1991");
                    userEntity.setAge(31);
//                        throw new DataAccessResourceFailureException("Something");
                    userRepository.save(userEntity)
                            .doOnNext(entity -> System.out.println(entity.getId()))
                            .subscribe();
                })
                .transform(ReactiveRabbit
                        .<Integer>rejectWhen(Exception.class)
                        .terminateWhen(Throwable.class)
                        .elseCommit())
                .doOnNext(txNumber -> System.out.println(String.format("Processed: %s", txNumber.get())))
                .subscribe(txNumber -> {
//                    expectedMessage.countDown();
                    log.error("exception happened");
                }, throwable -> {
                    System.out.println(String.format("Pipeline terminated: %s", Exceptions.unwrap(throwable)));
//                        log.error("Pipeline terminated", Exceptions.unwrap(throwable));
//                    expectedError.countDown();
                });
    }



}
*/
