package com.gildedrose.domain;

import com.gildedrose.domain.validation.Result;
import com.gildedrose.domain.validation.violations.InventoryDomainViolation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GeneralItemTest {

    @Test
    void givenCorrectGeneralItem_whenValidating_thenSuccess() {
        //given
        GeneralItem generalItem = GeneralItem.builder().name("GeneralItem").sellIn(3).quality(40).build();
        Result<GeneralItem> expected = Result.success(generalItem);

        //when
        Result<GeneralItem> actual = generalItem.validate();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenGeneralItemWithNegativeQuality_whenValidating_thenFailure() {
        //given
        GeneralItem generalItem = GeneralItem.builder().name("GeneralItem").sellIn(3).quality(-40).build();
        Result<GeneralItem> expected = Result.failure(new InventoryDomainViolation("Quality cannot be negative"));

        //when
        Result<GeneralItem> actual = generalItem.validate();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenGeneralIten_whenDegradeQuality_thenQualityDegraded() {
        //given
        GeneralItem generalItem = GeneralItem.builder().name("GeneralItem").sellIn(3).quality(40).build();

        //when
        generalItem.degradeQuality();

        //then
        Assertions.assertThat(generalItem.quality).isEqualTo(39);
    }
}
