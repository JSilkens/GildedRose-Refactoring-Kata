package com.gildedrose.usecase;

import com.gildedrose.domain.CanDegrade;
import com.gildedrose.domain.CanIncrease;
import com.gildedrose.domain.Item;
import com.gildedrose.domain.event.DomainEventPublisher;
import com.gildedrose.domain.event.ItemUpdatedEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;

/*
  In a typical application, the items would be fetched from a datasource.
 */
@RequiredArgsConstructor
@Transactional
public class UpdateInventoryUseCase {
    Item[] items;
    private final DomainEventPublisher domainEventPublisher;


    public UpdateInventoryUseCase(Item[] items, DomainEventPublisher domainEventPublisher) {
        this.items = items;
        this.domainEventPublisher = domainEventPublisher;
    }


    /*
    This could be called from a batch that will run at the end of each day.
     */
    public void updateQuality() {
        Arrays.stream(items)
            .filter(item -> item instanceof CanIncrease)
            .map(item -> (CanIncrease) item)
            .forEach(itemThatCanIncrease -> {
                itemThatCanIncrease.increaseQuality();
                Item updatedItem = (Item) itemThatCanIncrease;
                domainEventPublisher.publish(new ItemUpdatedEvent(updatedItem, LocalDateTime.now()));
            });

        Arrays.stream(items)
            .filter(item -> item instanceof CanDegrade)
            .map(i -> (CanDegrade) i)
            .forEach(itemThatCanDegrade -> {
                itemThatCanDegrade.degradeQuality();
                Item updatedItem = (Item) itemThatCanDegrade;
                domainEventPublisher.publish(new ItemUpdatedEvent(updatedItem, LocalDateTime.now()));
            });

        printItems();
    }

    private void printItems() {
        Arrays.stream(items).forEach(item -> System.out.println(item));
    }
}
