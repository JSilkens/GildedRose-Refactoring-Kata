package com.gildedrose.domain;

import com.gildedrose.domain.validation.CanValidate;
import com.gildedrose.domain.validation.Result;
import com.gildedrose.domain.validation.Validator;
import com.gildedrose.domain.validation.violations.InventoryDomainViolation;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.LoggerFactory;

@Getter
public class Conjured extends Item implements CanDegrade, CanValidate<Conjured> {

    @Builder
    private Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void degradeQuality() {
        quality = quality - 2;
    }

    @Override
    public Result<Conjured> validate() {
        return new Validator<>(() -> LoggerFactory.getLogger(this.getClass()))
            .validate(quality <= 50, () -> new InventoryDomainViolation("Quality cannot be higher than 50"))
            .validate(quality > 0, () -> new InventoryDomainViolation("Quality cannot be negative"))
            .evaluate(() -> this).map((o -> (Conjured) o));
    }
}
