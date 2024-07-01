package com.gildedrose.domain.event;

import com.gildedrose.domain.Item;

import java.time.LocalDateTime;

public class ItemCreatedEvent extends ItemEvent {

    public ItemCreatedEvent(Item item, LocalDateTime createdAt) {
        super(item, createdAt);
    }
}
