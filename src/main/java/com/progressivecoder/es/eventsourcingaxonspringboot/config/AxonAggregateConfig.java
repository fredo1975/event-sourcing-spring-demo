package com.progressivecoder.es.eventsourcingaxonspringboot.config;

import org.axonframework.common.jdbc.PersistenceExceptionResolver;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonAggregateConfig {
/*
    @Autowired
    public void configure(EventHandlingConfiguration config) {
        config.usingTrackingProcessors(); // default all processors to tracking mode.
    }
    
    // The Event store `EmbeddedEventStore` delegates actual storage and retrieval of events to an `EventStorageEngine`.
    @Bean
    public EmbeddedEventStore eventStore(EventStorageEngine storageEngine, AxonConfiguration configuration) {
        return new EmbeddedEventStore(storageEngine,configuration.messageMonitor(EventStore.class, "eventStore"));
    }

    // The JpaEventStorageEngine stores events in a JPA-compatible data source
    @Bean
    public EventStorageEngine storageEngine(Serializer defaultSerializer,
                                            PersistenceExceptionResolver persistenceExceptionResolver,
                                            @Qualifier("eventSerializer") Serializer eventSerializer,
                                            AxonConfiguration configuration,
                                            EntityManagerProvider entityManagerProvider,
                                            TransactionManager transactionManager) {
        return new JpaEventStorageEngine(defaultSerializer,configuration.upcasterChain(),persistenceExceptionResolver,eventSerializer,entityManagerProvider,transactionManager);
    }*/
}
