package com.gildedrose.usecase.fixtures;

import com.gildedrose.domain.*;


public class TestFixtures {

    private TestFixtures() {
        throw new UnsupportedOperationException();
    }

    public static Item[] itemsTestFixtures() {

        Item[] items = new Item[]{
            GeneralItem.builder().name("+5 Dexterity Vest").sellIn(10).quality(20).build(),
            AgedBrie.builder().name("Aged Brie").sellIn(2).quality(0).build(),
            GeneralItem.builder().name("Elixir of the Mongoose").sellIn(5).quality(7).build(),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //TODO item quality cannot be higher than 50
            new Item("Sulfuras, Hand of Ragnaros", -1, 80), //TODO
            BackstagePass.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(15).quality(20).build(),
            BackstagePass.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(10).quality(49).build(),
            BackstagePass.builder().name("Backstage passes to a TAFKAL80ETC concert").sellIn(5).quality(49).build(),
            Conjured.builder().name("Conjured Mana Cake").sellIn(3).quality(6).build()


        };


//        Item[] items = new Item[] {
//                new Item("+5 Dexterity Vest", 10, 20), //
//                new Item("Aged Brie", 2, 0), //
//                new Item("Elixir of the Mongoose", 5, 7), //
//                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
//                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
//                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
//                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
//                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
//                // this conjured item does not work properly yet
//                new Item("Conjured Mana Cake", 3, 6) };
//
//        GildedRose app = new GildedRose(items);
//
//        int days = 2;
//        if (args.length > 0) {
//            days = Integer.parseInt(args[0]) + 1;
//        }
//
//        for (int i = 0; i < days; i++) {
//            System.out.println("-------- day " + i + " --------");
//            System.out.println("name, sellIn, quality");
//            for (Item item : items) {
//                System.out.println(item);
//            }
//            System.out.println();
//            app.updateQuality();
//        }

        return items;
    }

}
