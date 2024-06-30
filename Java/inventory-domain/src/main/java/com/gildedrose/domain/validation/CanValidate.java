package com.gildedrose.domain.validation;

public interface CanValidate<T> {
    Result<T> validate();
}
