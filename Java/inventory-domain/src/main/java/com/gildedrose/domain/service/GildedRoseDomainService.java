package com.gildedrose.domain.service;

import com.gildedrose.domain.Item;
import com.gildedrose.domain.event.ItemCreatedEvent;
import com.gildedrose.domain.event.ItemUpdatedEvent;
import com.gildedrose.domain.validation.CanValidate;

public interface GildedRoseDomainService {

    ItemCreatedEvent createItem(CanValidate<Item> item);

    ItemUpdatedEvent updateItem(CanValidate<Item> item);
}
