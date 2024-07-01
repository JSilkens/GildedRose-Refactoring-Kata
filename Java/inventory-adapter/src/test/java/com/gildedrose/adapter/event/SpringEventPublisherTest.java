package com.gildedrose.adapter.event;

import com.gildedrose.domain.event.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SpringEventPublisherTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    private SpringEventPublisher springEventPublisher;

    @BeforeEach
    void setUp() {
        springEventPublisher = new SpringEventPublisher(applicationEventPublisher);
    }

    @Test
    void givenDomainEvent_whenPublish_thenPublishEventThroughApplicationEventPublisher() {
        //given
        DummyDomainEvent dummyDomainEvent = new DummyDomainEvent("Mijn naam is haas");

        //when
        springEventPublisher.publish(dummyDomainEvent);

        //then
        verify(applicationEventPublisher).publishEvent(dummyDomainEvent);


    }

    public record DummyDomainEvent(String value) implements DomainEvent<DummyDomainEvent> {

    }
}
