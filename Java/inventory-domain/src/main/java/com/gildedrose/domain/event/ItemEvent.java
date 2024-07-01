package com.gildedrose.domain.event;

import com.gildedrose.domain.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public abstract class ItemEvent implements DomainEvent<Item> {

    private final Item item;
    private final LocalDateTime createdAt;

    @Override
    public String toString() {
        return "ItemEvent{" +
            "item=" + item.name +
            ", createdAt=" + createdAt +
            '}';
    }
}
