package com.gildedrose.domain.validation;

import com.gildedrose.domain.validation.error.FunctionalError;
import com.gildedrose.domain.validation.exception.TechnicalException;
import com.gildedrose.domain.validation.violations.InventoryDomainViolation;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {
    @Test
    public void whenCreatingSuccessResult_thenNoErrorsAndValueIsPresent() {
        // Given
        String expectedValue = "Test";

        // When
        Result<String> result = Result.success(expectedValue);

        // Then
        assertFalse(result.hasErrors());
        assertEquals(expectedValue, result.getValue());
    }

    @Test
    public void whenCreatingFailureResult_thenErrorsArePresentAndNoValue() {
        // Given
        FunctionalError error = new InventoryDomainViolation("Error message");

        // When
        Result<String> result = Result.failure(error);

        // Then
        assertTrue(result.hasErrors());
        assertEquals(1, result.getErrors().size());
    }

    @Test
    public void whenCreatingSuccessWithNullValue_thenTechnicalExceptionIsThrown() {


        // When & Then
        assertThrows(TechnicalException.class, () -> Result.success(null));
    }

    @Test
    public void whenCreatingFailureWithMultipleErrors_thenErrorsArePresentAndNoValue() {
        // Given
        List<FunctionalError> errors = Arrays.asList(new InventoryDomainViolation("Error1"), new InventoryDomainViolation("Error2"));

        // When
        Result<String> result = Result.failure(errors);

        // Then
        assertTrue(result.hasErrors());
        assertEquals(2, result.getErrors().size());
    }

    @Test
    public void whenFilteringResultWithPredicateNotSatisfied_thenResultHasAdditionalError() {
        // Given
        Result<Integer> result = Result.success(10);
        FunctionalError additionalError = new InventoryDomainViolation("Invalid");

        // When
        Result<Integer> filteredResult = result.filter(x -> x > 20, () -> additionalError);

        // Then
        assertTrue(filteredResult.hasErrors());
        assertTrue(filteredResult.getErrors().contains(additionalError));
    }

    @Test
    public void whenFilteringResultWithPredicateSatisfied_thenNoAdditionalError() {
        // Given
        Result<Integer> result = Result.success(25);
        FunctionalError additionalError = new InventoryDomainViolation("Invalid");

        // When
        Result<Integer> filteredResult = result.filter(x -> x > 20, () -> additionalError);

        // Then
        assertFalse(filteredResult.hasErrors());
        assertEquals(result, filteredResult);
    }

    @Test
    public void whenMappingResultWithFunction_thenTransformedValueIsReturned() {
        // Given
        Result<Integer> result = Result.success(5);

        // When
        Result<String> mappedResult = result.map(Object::toString);

        // Then
        assertFalse(mappedResult.hasErrors());
        assertEquals("5", mappedResult.getValue());
    }

    @Test
    public void whenFlatMappingResultWithFunctionProvidingNewResult_thenNewResultIsReturned() {
        // Given
        Result<Integer> result = Result.success(50);

        // When
        Result<String> flatMappedResult = result.flatMap(num -> Result.success("Value: " + num));

        // Then
        assertFalse(flatMappedResult.hasErrors());
        assertEquals("Value: 50", flatMappedResult.getValue());
    }

    @Test
    public void whenGetOrElseWithValue_thenReturnsValue() {
        // Given
        Result<Integer> result = Result.success(10);

        // When
        Integer value = result.getOrElse(0);

        // Then
        assertEquals(10, value);
    }

    @Test
    public void whenGetOrElseWithNoValue_thenReturnsDefault() {
        // Given
        FunctionalError error = new InventoryDomainViolation("Error");
        Result<Integer> result = Result.failure(error);

        // When
        Integer value = result.getOrElse(0);

        // Then
        assertEquals(0, value);
    }

    @Test
    public void whenGetOrElseWithSupplierAndNoValue_thenReturnsDefaultFromSupplier() {
        // Given
        InventoryDomainViolation error = new InventoryDomainViolation("Error");
        Result<Integer> result = Result.failure(error);

        // When
        Integer value = result.getOrElse(() -> 42);

        // Then
        assertEquals(42, value);
    }

    @Test
    public void whenToOptionalWithValue_thenOptionalIsPresent() {
        // Given
        Result<String> result = Result.success("Test");

        // When
        Optional<String> optional = result.toOptional();

        // Then
        assertTrue(optional.isPresent());
        assertEquals("Test", optional.get());
    }

    @Test
    public void whenToOptionalWithNoValue_thenOptionalIsEmpty() {
        // Given
        InventoryDomainViolation error = new InventoryDomainViolation("Error");
        Result<String> result = Result.failure(error);

        // When
        Optional<String> optional = result.toOptional();

        // Then
        assertFalse(optional.isPresent());
    }

    @Test
    public void whenMergingErrorsFromMultipleResults_thenDistinctErrorsAreAggregated() {
        // Given
        Result<Integer> result1 = Result.failure(new InventoryDomainViolation("Error1"));
        Result<Integer> result2 = Result.failure(new InventoryDomainViolation("Error2"));
        Result<Integer> result3 = Result.failure(new InventoryDomainViolation("Error1")); // Duplicate error

        // When
        java.util.List<FunctionalError> errors = Result.mergeErrors(Arrays.asList(result1, result2, result3));

        // Then
        assertEquals(2, errors.size());
    }

    @Test
    public void whenCreatingFailureWithEmptyErrorList_thenNoErrors() {
        // Given
        List<FunctionalError> errors = Collections.emptyList();

        // When
        Result<String> result = Result.failure(errors);

        // Then
        assertFalse(result.hasErrors());
        assertEquals(0, result.getErrors().size());
    }
}
