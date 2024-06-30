package com.gildedrose.domain;

import com.gildedrose.domain.validation.CanValidate;
import com.gildedrose.domain.validation.Result;
import com.gildedrose.domain.validation.Validator;
import com.gildedrose.domain.validation.violations.InventoryDomainViolation;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.LoggerFactory;

@Getter
public class AgedBrie extends Item implements CanIncrease, CanDegrade, CanValidate<AgedBrie> {

    private final int age;

    @Builder
    private AgedBrie(String name, int sellIn, int quality, int age) {
        super(name, sellIn, quality);
        this.age = age;
    }

    @Override
    public void increaseQuality() {
        int calculatedQuality = quality + age;

        if (calculatedQuality <= 50) {
            quality = calculatedQuality;
        } else {
            quality = 50;
        }
    }

    @Override
    public void degradeQuality() {
        if (sellIn < 0) {
            quality = quality - 2;
        } else if (quality >= 0) {
            quality--;
        }
    }

    @Override
    public Result<AgedBrie> validate() {
        return new Validator<>(() -> LoggerFactory.getLogger(this.getClass()))
            .validate(quality <= 50, () -> new InventoryDomainViolation("Quality cannot be higher than 50"))
            .validate(quality >= 0, () -> new InventoryDomainViolation("Quality cannot be negative"))
            .validate(age >= 0, () -> new InventoryDomainViolation("Age cannot be negative"))
            .evaluate(() -> this).map((o) -> (AgedBrie) o);
    }


}
