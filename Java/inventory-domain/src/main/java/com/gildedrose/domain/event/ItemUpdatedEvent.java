package com.gildedrose.domain.event;

import com.gildedrose.domain.Item;

import java.time.LocalDateTime;

public class ItemUpdatedEvent extends ItemEvent {

    public ItemUpdatedEvent(Item item, LocalDateTime createdAt) {
        super(item, createdAt);
    }
}
