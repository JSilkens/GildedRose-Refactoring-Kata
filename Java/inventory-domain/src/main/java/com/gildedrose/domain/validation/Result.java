package com.gildedrose.domain.validation;

import com.gildedrose.domain.validation.error.FunctionalError;
import com.gildedrose.domain.validation.exception.TechnicalException;
import com.gildedrose.domain.validation.util.CollectionUtil;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Result<T> {
    private final T value;
    private final List<FunctionalError> errors;

    public T getValue() {
        return value;
    }

    public List<FunctionalError> getErrors() {
        return errors;
    }

    public static <T> Result<T> success(T value) {
        if (isNull(value)) {
            throw new TechnicalException("Null value is not allowed if a result is successful!");
        }
        return new Result<>(value, Collections.emptyList());
    }


    public boolean isSuccess() {
        return errors.isEmpty() && nonNull(value);
    }

    public static <T> Result<T> failure(FunctionalError error) {
        return new Result<T>(null, Collections.singletonList(error));
    }

    public static <T> Result<T> failure(List<FunctionalError> errors) {
        return new Result<>(null, errors);
    }

    public Result<T> filter(Predicate<? super T> predicate, Supplier<FunctionalError> error) {
        if (isNull(value)) {
            return Result.failure(errors);
        } else {
            return predicate.test(value) ? Result.success(value) : Result.failure(CollectionUtil.join(error.get(), errors));
        }
    }

    public <U> Result<U> map(Function<? super T, U> function) {
        return nonNull(value) && errors.isEmpty() ? Result.success(function.apply(value)) : Result.failure(errors);
    }

    public <U> Result<U> flatMap(Function<? super T, Result<U>> f) {
        return nonNull(value) && errors.isEmpty() ? f.apply(value) : Result.failure(errors);
    }

    public T getOrElse(T other) {
        return nonNull(value) ? value : other;
    }

    public T getOrElse(Supplier<? extends T> other) {
        return nonNull(value) ? value : other.get();
    }

    public Optional<T> toOptional() {
        return this.map(Optional::of).getOrElse(Optional.empty());
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public static List<FunctionalError> mergeErrors(Collection<Result<?>> results) {
        return results.stream()
            .map(Result::getErrors)
            .flatMap(Collection::stream)
            .distinct()
            .collect(Collectors.toList());
    }


}
