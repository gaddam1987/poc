package gaddam1987.github.poc.axon.configuration;


import gaddam1987.github.poc.axon.model.Account;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.contextsupport.spring.AnnotationDriven;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;


@Configuration
@AnnotationDriven
public class AxonConfiguration {

    @Bean
    @ConditionalOnMissingBean
    CommandBus commandBus() {
        SimpleCommandBus bus = new SimpleCommandBus();
        return bus;
    }

    @Bean
    @ConditionalOnMissingBean
    CommandGateway commandGateway() {
        return new DefaultCommandGateway(commandBus());
    }

    @Bean
    @ConditionalOnMissingBean
    EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean
    @ConditionalOnMissingBean
    EventStore eventStore() {
        EventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("./events")));
        return eventStore;
    }

    @Bean
    AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
        AnnotationCommandHandlerBeanPostProcessor p =  new AnnotationCommandHandlerBeanPostProcessor();
        p.setCommandBus(commandBus());
        return p;
    }

    /**
     * Our aggregate root is now created from stream of events and not from a representation in a persistent mechanism,
     * thus we need a repository that can handle the retrieving of our aggregate root from the stream of events.
     *
     * We configure the EventSourcingRepository which does exactly this. We supply it with the event store
     * @return {@link EventSourcingRepository}
     */
    @Bean
    public EventSourcingRepository eventSourcingRepository() {
        EventSourcingRepository eventSourcingRepository = new EventSourcingRepository(Account.class, eventStore());
        eventSourcingRepository.setEventBus(eventBus());
        return eventSourcingRepository;
    }


}
