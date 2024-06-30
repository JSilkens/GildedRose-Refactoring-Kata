package com.gildedrose.domain;

import com.gildedrose.domain.validation.CanValidate;
import com.gildedrose.domain.validation.Result;
import com.gildedrose.domain.validation.Validator;
import com.gildedrose.domain.validation.violations.InventoryDomainViolation;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.LoggerFactory;

@Getter
public class BackstagePass extends Item implements CanIncrease, CanDegrade, CanValidate<BackstagePass> {

    @Builder
    private BackstagePass(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void degradeQuality() {
        if (sellIn < 0) {
            quality = 0;
        } else if (quality >= 0) {
            quality--;
        }
    }

    @Override
    public void increaseQuality() {
        if (sellIn > 10) {
            if (quality < 50) {
                quality++;
            }
        } else if (sellIn > 5) {
            if (quality < 50) {
                quality += 2;
            }
        } else if (sellIn > 0) {
            if (quality < 50) {
                quality += 3;
            }
        } else {
            quality = 0;
        }

        if (quality > 50) {
            quality = 50;
        }
    }


    @Override
    public Result<BackstagePass> validate() {
        return new Validator<>(() -> LoggerFactory.getLogger(this.getClass()))
            .validate(quality <= 50, () -> new InventoryDomainViolation("Quality cannot be higher than 50"))
            .validate(quality > 0, () -> new InventoryDomainViolation("Quality cannot be negative"))
            .evaluate(() -> this).map((o -> (BackstagePass) o));
    }
}
