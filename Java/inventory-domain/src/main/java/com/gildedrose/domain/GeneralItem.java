package com.gildedrose.domain;

import com.gildedrose.domain.validation.CanValidate;
import com.gildedrose.domain.validation.Result;
import com.gildedrose.domain.validation.Validator;
import com.gildedrose.domain.validation.violations.InventoryDomainViolation;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.LoggerFactory;

@Getter
public class GeneralItem extends Item implements CanDegrade, CanValidate<GeneralItem> {

    @Builder
    private GeneralItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void degradeQuality() {
        if (quality >= 0) {
            quality--;
        }
    }

    @Override
    public Result<GeneralItem> validate() {
        return new Validator<>(() -> LoggerFactory.getLogger(this.getClass()))
            .validate(quality <= 50, () -> new InventoryDomainViolation("Quality cannot be higher than 50"))
            .validate(quality > 0, () -> new InventoryDomainViolation("Quality cannot be negative"))
            .evaluate(() -> this).map((o -> (GeneralItem) o));
    }
}
