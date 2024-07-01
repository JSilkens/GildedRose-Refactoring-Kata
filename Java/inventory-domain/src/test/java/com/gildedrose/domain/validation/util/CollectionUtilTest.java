package com.gildedrose.domain.validation.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionUtilTest {

    @Test
    void givenTwoNonEmptyLists_whenJoin_thenJoinedListContainsAllElements() {
        //given
        List<Integer> first = Arrays.asList(1, 2, 3);
        List<Integer> second = Arrays.asList(4, 5, 6);

        //when
        List<Integer> result = CollectionUtil.join(first, second);

        //then
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void givenFirstListIsNull_whenJoin_thenJoinedListContainsElementsOfSecond() {
        //given
        List<Integer> first = null;
        List<Integer> second = Arrays.asList(4, 5, 6);

        //when
        List<Integer> result = CollectionUtil.join(first, second);

        //then
        assertThat(result).containsExactly(4, 5, 6);
    }

    @Test
    void givenSecondListIsNull_whenJoin_thenJoinedListContainsElementsOfFirst() {
        //given
        List<Integer> first = Arrays.asList(1, 2, 3);
        List<Integer> second = null;

        //when
        List<Integer> result = CollectionUtil.join(first, second);

        //then
        assertThat(result).containsExactly(1, 2, 3);
    }

    @Test
    void givenBothListsAreNull_whenJoin_thenJoinedListIsEmpty() {
        //given
        List<Integer> first = null;
        List<Integer> second = null;

        //when
        List<Integer> result = CollectionUtil.join(first, second);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    void givenFirstElementAndNonEmptyList_whenJoin_thenJoinedListContainsAllElements() {
        //given
        Integer first = 1;
        List<Integer> second = Arrays.asList(2, 3, 4);

        //when
        List<Integer> result = CollectionUtil.join(first, second);

        //then
        assertThat(result).containsExactly(1, 2, 3, 4);
    }

    @Test
    void givenFirstElementIsNullAndNonEmptyList_whenJoin_thenJoinedListContainsElementsOfSecond() {
        //given
        Integer first = null;
        List<Integer> second = Arrays.asList(2, 3, 4);

        //when
        List<Integer> result = CollectionUtil.join(first, second);

        //then
        assertThat(result).containsExactly(2, 3, 4);
    }

    @Test
    void givenFirstElementAndSecondListIsNull_whenJoin_thenJoinedListContainsFirstElement() {
        //given
        Integer first = 1;
        List<Integer> second = null;

        //when
        List<Integer> result = CollectionUtil.join(first, second);

        //then
        assertThat(result).containsExactly(1);
    }

    @Test
    void givenFirstElementIsNullAndSecondListIsNull_whenJoin_thenJoinedListIsEmpty() {
        //given
        Integer first = null;
        List<Integer> second = null;

        //when
        List<Integer> result = CollectionUtil.join(first, second);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    void givenEmptyFirstListAndEmptySecondList_whenJoin_thenJoinedListIsEmpty() {
        //given
        List<Integer> first = Collections.emptyList();
        List<Integer> second = Collections.emptyList();

        //when
        List<Integer> result = CollectionUtil.join(first, second);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    void givenEmptyFirstListAndNonEmptySecondList_whenJoin_thenJoinedListContainsElementsOfSecond() {
        //given
        List<Integer> first = Collections.emptyList();
        List<Integer> second = Arrays.asList(1, 2, 3);

        //when
        List<Integer> result = CollectionUtil.join(first, second);

        //then
        assertThat(result).containsExactly(1, 2, 3);
    }

    @Test
    void givenNonEmptyFirstListAndEmptySecondList_whenJoin_thenJoinedListContainsElementsOfFirst() {
        //given
        List<Integer> first = Arrays.asList(1, 2, 3);
        List<Integer> second = Collections.emptyList();

        //when
        List<Integer> result = CollectionUtil.join(first, second);

        //then
        assertThat(result).containsExactly(1, 2, 3);
    }
}
