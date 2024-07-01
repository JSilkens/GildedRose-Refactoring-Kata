package com.gildedrose.domain;

import com.gildedrose.domain.validation.Result;
import com.gildedrose.domain.validation.violations.InventoryDomainViolation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BackstagePassTest {

    @Test
    void givenCorrectBackstagePass_whenValidating_thenSuccess() {
        //given
        BackstagePass backstagePass = BackstagePass.builder()
            .name("Backstage pass 1")
            .sellIn(10)
            .quality(50)
            .build();

        Result<BackstagePass> expected = Result.success(backstagePass);

        //when
        Result<BackstagePass> actual = backstagePass.validate();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenBackstagePassWithQualityAboveFifty_whenValidating_thenFailure() {
        //given
        BackstagePass backstagePass = BackstagePass.builder()
            .name("Backstage pass 1")
            .sellIn(10)
            .quality(500)
            .build();

        Result<BackstagePass> expected = Result.failure(new InventoryDomainViolation("Quality cannot be higher than 50"));

        //when
        Result<BackstagePass> actual = backstagePass.validate();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenBackstagePassWithNegativeQuality_whenValidating_thenFailure() {
        //given
        BackstagePass backstagePass = BackstagePass.builder()
            .name("Backstage pass 1")
            .sellIn(10)
            .quality(-5)
            .build();

        Result<BackstagePass> expected = Result.failure(new InventoryDomainViolation("Quality cannot be negative"));

        //when
        Result<BackstagePass> actual = backstagePass.validate();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenBackstagePassToSellinSevenDays_whenIncreaseQuality_thenQualityIncreased() {
        //given
        BackstagePass backstagePass = BackstagePass.builder()
            .name("Backstage pass 1")
            .sellIn(7)
            .quality(20)
            .build();

        //when
        backstagePass.increaseQuality();

        //then
        assertThat(backstagePass.quality).isEqualTo(22);
    }

    @Test
    void givenBackstagePassToSellinFourDays_whenIncreaseQuality_thenQualityIncreased() {
        //given
        BackstagePass backstagePass = BackstagePass.builder()
            .name("Backstage pass 1")
            .sellIn(4)
            .quality(20)
            .build();

        //when
        backstagePass.increaseQuality();

        //then
        assertThat(backstagePass.quality).isEqualTo(23);
    }

    @Test
    void givenBackstagePassToExpiredConcert_whenDecreaseQuality_thenQualityZero() {
        //given
        BackstagePass backstagePass = BackstagePass.builder()
            .name("Backstage pass 1")
            .sellIn(-1)
            .quality(20)
            .build();

        //when
        backstagePass.degradeQuality();

        //then
        assertThat(backstagePass.quality).isEqualTo(0);
    }

    // Additional tests

    @Test
    void givenBackstagePassWithSellInGreaterThanTen_whenIncreaseQuality_thenQualityIncreasedByOne() {
        //given
        BackstagePass backstagePass = BackstagePass.builder()
            .name("Backstage pass 1")
            .sellIn(15)
            .quality(20)
            .build();

        //when
        backstagePass.increaseQuality();

        //then
        assertThat(backstagePass.quality).isEqualTo(21);
    }

    @Test
    void givenBackstagePassWithQualityFifty_whenIncreaseQuality_thenQualityRemainsFifty() {
        //given
        BackstagePass backstagePass = BackstagePass.builder()
            .name("Backstage pass 1")
            .sellIn(5)
            .quality(50)
            .build();

        //when
        backstagePass.increaseQuality();

        //then
        assertThat(backstagePass.quality).isEqualTo(50);
    }

    @Test
    void givenBackstagePassWithSellInZero_whenIncreaseQuality_thenQualityDropsToZero() {
        //given
        BackstagePass backstagePass = BackstagePass.builder()
            .name("Backstage pass 1")
            .sellIn(0)
            .quality(20)
            .build();

        //when
        backstagePass.increaseQuality();

        //then
        assertThat(backstagePass.quality).isEqualTo(0);
    }

    @Test
    void givenBackstagePassWithSellInFiveAndQualityFortyEight_whenIncreaseQuality_thenQualityIncreasedToFifty() {
        //given
        BackstagePass backstagePass = BackstagePass.builder()
            .name("Backstage pass 1")
            .sellIn(5)
            .quality(48)
            .build();

        //when
        backstagePass.increaseQuality();

        //then
        assertThat(backstagePass.quality).isEqualTo(50);
    }

    @Test
    void givenBackstagePassWithNegativeSellIn_whenDecreaseQuality_thenQualityRemainsZero() {
        //given
        BackstagePass backstagePass = BackstagePass.builder()
            .name("Backstage pass 1")
            .sellIn(-5)
            .quality(0)
            .build();

        //when
        backstagePass.degradeQuality();

        //then
        assertThat(backstagePass.quality).isEqualTo(0);
    }
}
