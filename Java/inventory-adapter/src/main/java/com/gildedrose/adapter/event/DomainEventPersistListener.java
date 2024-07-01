package com.gildedrose.adapter.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gildedrose.domain.event.DomainEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DomainEventPersistListener {

    private final ObjectMapper objectMapper;

    @EventListener
    @Transactional(Transactional.TxType.MANDATORY)
    public void persistDomainEvent(DomainEvent domainEvent) {
        /*
        Logic for persisting domain events goes here.

        1. Use an ObjectMapper to write the value as a String
        2. Persist the value to a database
         */

        log.info("Persisting domain event: {}", domainEvent);
    }
}
