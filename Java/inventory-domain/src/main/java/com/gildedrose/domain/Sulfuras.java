package com.gildedrose.domain;

import lombok.Builder;

public class Sulfuras extends Item {

    private Sulfuras(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Builder
    public Sulfuras() {
        super(null, 0, 0);
    }
}
