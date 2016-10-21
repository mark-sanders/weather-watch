package com.github.marksanders.scratch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class OptionalCoerce {

    public static <T> Optional<T> coerce(
            final Optional<Object> item,
            final Class<T> requiredClass) {

        return item
                .filter(requiredClass::isInstance)
                .map(requiredClass::cast);
    }

    @Test
    public void testCoerceEmptyToDouble() {
        Optional<Object> item = Optional.ofNullable(null);
        Optional<Double> result = coerce(item, Double.class);
        assertFalse(result.isPresent());
        assertEquals(Optional.empty(), result);
    }
    
    @Test
    public void testCoerceStringToDouble() {
        Optional<Object> item = Optional.of("string");
        Optional<Double> result = coerce(item, Double.class);
        assertFalse(result.isPresent());
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testCoerceDoubleToDouble() {
        Optional<Object> item = Optional.of(123.45);
        Optional<Double> result = coerce(item, Double.class);
        assertTrue(result.isPresent());
        assertEquals(new Double(123.45), result.get());
    }

    @Test
    public void testCoerceEmptyToString() {
        Optional<Object> item = Optional.ofNullable(null);
        Optional<String> result = coerce(item, String.class);
        assertFalse(result.isPresent());
        assertEquals(Optional.empty(), result);
    }
    
    @Test
    public void testCoerceStringToString() {
        Optional<Object> item = Optional.of("string");
        Optional<String> result = coerce(item, String.class);
        assertTrue(result.isPresent());
        assertEquals("string", result.get());
    }

    @Test
    public void testCoerceDoubleToString() {
        Optional<Object> item = Optional.of(123.45);
        Optional<String> result = coerce(item, String.class);
        assertFalse(result.isPresent());
        assertEquals(Optional.empty(), result);
    }

}
