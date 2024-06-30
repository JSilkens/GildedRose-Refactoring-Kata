package com.gildedrose.domain.validation.error;

import java.io.Serializable;

public interface FunctionalError extends Serializable {

    String message();

    default String print() {
        return getClass().getSimpleName() + ": Message: " + message();
    }

}
