package com.gildedrose.domain.event;

public interface DomainEventPublisher {
    void publish(DomainEvent domainEvent);
}
