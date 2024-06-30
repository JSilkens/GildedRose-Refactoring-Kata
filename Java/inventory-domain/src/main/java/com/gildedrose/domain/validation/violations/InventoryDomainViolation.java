package com.gildedrose.domain.validation.violations;

import com.gildedrose.domain.validation.error.FunctionalError;

import java.io.Serializable;

public record InventoryDomainViolation(String message) implements Serializable, FunctionalError {
}
