package com.github.marksanders.scratch;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;

public class OptionalCoerceWithDefault {

    public static <T> T coerce(
            final Optional<Object> source,
            final Class<T> requiredClass,
            final T other) {

        return source
                .filter(requiredClass::isInstance)
                .map(requiredClass::cast)
                .orElse(other);
    }

    @Test
    public void testCoerceEmptyToDouble() {
        Double doubleDefault = new Double(99.9);

        Optional<Object> item = Optional.ofNullable(null);
        Double result = coerce(item, Double.class, doubleDefault);
        assertEquals(doubleDefault, result);
    }
    
    @Test
    public void testCoerceStringToDouble() {
        Double doubleDefault = new Double(99.9);
        
        Optional<Object> item = Optional.of("string");
        Double result = coerce(item, Double.class, doubleDefault);
        assertEquals(doubleDefault, result);
    }

    @Test
    public void testCoerceDoubleToDouble() {
        Double doubleDefault = new Double(99.9);
        Double source = new Double(123.45);
        
        Optional<Object> item = Optional.of(source);
        Double result = coerce(item, Double.class, doubleDefault);
        assertEquals(source, result);
    }

    @Test
    public void testCoerceEmptyToString() {
        Optional<Object> item = Optional.ofNullable(null);
        String result = coerce(item, String.class, "default");
        assertEquals("default", result);
    }
    
    @Test
    public void testCoerceStringToString() {
        Optional<Object> item = Optional.of("string");
        String result = coerce(item, String.class, "default");
        assertEquals("string", result);
    }

    @Test
    public void testCoerceDoubleToString() {
        Double source = new Double(123.45);
        
        Optional<Object> item = Optional.of(source);
        String result = coerce(item, String.class, "default");
        assertEquals("default", result);
    }

}
