package com.gildedrose.adapter.event;


import com.gildedrose.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DomainEventPersistListener {

    public void persistDomainEvent(DomainEvent domainEvent) {
        /*
        Logic for persisting domain events goes here.

        1. Use an ObjectMapper to write the value as a String
        2. Persist the value to an database
         */
    }
}
