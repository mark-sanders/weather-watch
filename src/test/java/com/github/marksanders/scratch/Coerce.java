package com.github.marksanders.scratch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class Coerce {

    public static Double coerceToDouble(final Object item) {
        if (item instanceof Double) {
            return (Double) item;
        }
        
        return null;
    }

    @Test
    public void testCoerceNull() {
        Double result = coerceToDouble(null);
        assertNull(result);
    }
    
    @Test
    public void testCoerceString() {
        Double result = coerceToDouble("string");
        assertNull(result);
    }

    @Test
    public void testCoerceDouble() {
        Object item = 123.45;
        Double result = coerceToDouble(item);
        assertEquals(new Double(123.45), result);
    }

}
