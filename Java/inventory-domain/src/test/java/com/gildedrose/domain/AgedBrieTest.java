package com.gildedrose.domain;

import com.gildedrose.domain.validation.Result;
import com.gildedrose.domain.validation.violations.InventoryDomainViolation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AgedBrieTest {

    @Test
    void givenCorrectAgedBrieToValidate_whenDoValidation_thenFailure() {
        //given
        AgedBrie agedBrie = AgedBrie.builder()
            .age(0)
            .name("Aged Brie 1")
            .sellIn(50)
            .quality(20)
            .build();

        Result<AgedBrie> expected = Result.success(agedBrie);

        //when
        Result<AgedBrie> actual = agedBrie.validate();

        //then
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void givenAgedBrieWithQualityHigherThan50_whenDoingValidation_thenFailure() {
        //given
        AgedBrie agedBrie = AgedBrie.builder()
            .age(0)
            .name("Aged Brie 1")
            .sellIn(50)
            .quality(70)
            .build();

        Result<AgedBrie> expected = Result.failure(new InventoryDomainViolation("Quality cannot be higher than 50"));

        //when
        Result<AgedBrie> actual = agedBrie.validate();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenAgedBrieWithNegativeQuality_whenDoingValidation_thenFailure() {
        //given
        AgedBrie agedBrie = AgedBrie.builder()
            .age(0)
            .name("Aged Brie 1")
            .sellIn(50)
            .quality(-2)
            .build();

        Result<AgedBrie> expected = Result.failure(new InventoryDomainViolation("Quality cannot be negative"));

        //when
        Result<AgedBrie> actual = agedBrie.validate();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenAgedBrieWithNegativeAge_whenDoingValidation_thenFailure() {
        //given
        AgedBrie agedBrie = AgedBrie.builder()
            .age(-1)
            .name("Aged Brie 1")
            .sellIn(50)
            .quality(20)
            .build();

        Result<AgedBrie> expected = Result.failure(new InventoryDomainViolation("Age cannot be negative"));

        //when
        Result<AgedBrie> actual = agedBrie.validate();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenAgedBrie_whenIncreaseQuality_thenQualityIncreased() {
        //given
        AgedBrie agedBrie = AgedBrie.builder()
            .age(1)
            .name("Aged Brie 1")
            .sellIn(50)
            .quality(20)
            .build();

        //when
        agedBrie.increaseQuality();

        //then
        assertThat(agedBrie.quality).isEqualTo(21);
    }

    @Test
    void givenVeryAgedBrie_whenIncreaseQuality_thenQualityNotHigherThanFifty() {
        //given
        AgedBrie agedBrie = AgedBrie.builder()
            .age(100)
            .name("Aged Brie 1")
            .sellIn(50)
            .quality(20)
            .build();

        //when
        agedBrie.increaseQuality();

        //then
        assertThat(agedBrie.quality).isEqualTo(50);
    }

    @Test
    void givenAgedBrieWithNegativeSellIn_whenDoingValidation_thenFailure() {
        //given
        AgedBrie agedBrie = AgedBrie.builder()
            .age(0)
            .name("Aged Brie 1")
            .sellIn(-1)
            .quality(40)
            .build();

        Result<AgedBrie> expected = Result.failure(new InventoryDomainViolation("Sell in cannot be negative"));

        //when
        Result<AgedBrie> actual = agedBrie.validate();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenAgedBrieWithNegativeSellInDate_whenDegradeQuality_thenQualityDegraded() {
        //given
        AgedBrie agedBrie = AgedBrie.builder()
            .age(0)
            .name("Aged Brie 1")
            .sellIn(-1)
            .quality(40)
            .build();

        //when

        agedBrie.degradeQuality();

        //then
        assertThat(agedBrie.quality).isEqualTo(38);
    }

    @Test
    void givenAgedBrieWithPositiveSellInDate_whenDegradeQuality_thenQualityNotDegraded() {
        //given
        AgedBrie agedBrie = AgedBrie.builder()
            .age(0)
            .name("Aged Brie 1")
            .sellIn(1)
            .quality(40)
            .build();

        //when

        agedBrie.degradeQuality();

        //then
        assertThat(agedBrie.quality).isEqualTo(40);
    }
}
