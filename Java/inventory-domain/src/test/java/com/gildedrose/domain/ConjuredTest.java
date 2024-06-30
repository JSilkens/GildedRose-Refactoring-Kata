package com.gildedrose.domain;

import com.gildedrose.domain.validation.Result;
import com.gildedrose.domain.validation.violations.InventoryDomainViolation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ConjuredTest {

    @Test
    void givenConjuredToValidate_whenValidating_thenSuccess() {
        //given
        Conjured conjured = Conjured.builder()
            .name("Conjured 1")
            .sellIn(1)
            .quality(50)
            .build();

        Result<Conjured> expected = Result.success(conjured);

        //when
        Result<Conjured> actual = conjured.validate();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenConjuredWithQualityHigherThanFiveHundred_whenValidating_thenFailure() {
        //given
        Conjured conjured = Conjured.builder()
            .name("Conjured 1")
            .sellIn(1)
            .quality(500)
            .build();

        Result<Conjured> expected = Result.failure(new InventoryDomainViolation("Quality cannot be higher than 50"));

        //when
        Result<Conjured> actual = conjured.validate();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenConjuredWithNegativeQuality_whenValidating_thenFailure() {
        //given
        Conjured conjured = Conjured.builder()
            .name("Conjured 1")
            .sellIn(1)
            .quality(50)
            .build();

        Result<Conjured> expected = Result.failure(new InventoryDomainViolation("Quality cannot be negative"));

        //when
        Result<Conjured> actual = conjured.validate();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenCorrectConjured_whenDegradeQuality_thenQualityDegraded() {
        //given
        Conjured conjured = Conjured.builder()
            .name("Conjured 1")
            .sellIn(1)
            .quality(50)
            .build();

        //when
        conjured.degradeQuality();
        //then
        Assertions.assertThat(conjured.quality).isEqualTo(48);
    }


}
