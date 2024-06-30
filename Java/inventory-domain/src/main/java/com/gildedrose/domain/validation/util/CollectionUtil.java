package com.gildedrose.domain.validation.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CollectionUtil {
    public static <T> List<T> join(List<? extends T> first, List<? extends T> second) {
        if (isNull(first)) {
            return join(emptyList(), second);
        } else if (isNull(second)) {
            return join(first, emptyList());
        } else {
            return Stream.of(first, second)
                .flatMap(Collection::parallelStream)
                .collect(toImmutableList());
        }
    }

    public static <T, U extends T> List<T> join(U first, List<? extends T> second) {
        return isNull(first) ? join(emptyList(), second) : join(singletonList(first), second);
    }


}
