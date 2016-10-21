package com.github.marksanders.scratch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class FindFirstExample {

    @Test
    public void findFirstExample1() {
        List<String> planets = 
                Arrays.asList(
                        "Venus",
                        "Earth",
                        "Mars",
                        "Jupiter",
                        "Saturn",
                        "Uranus",
                        "Neptune");

        Optional<String> result = planets.stream()
                .filter(planet -> planet.endsWith("une"))
                .findFirst();
        
        result.ifPresent(System.out::println);
        
        assertTrue(result.isPresent());
        assertEquals("Neptune", result.get());
    }

    @Test
    public void findFirstExample() {
        List<String> planets = 
                Arrays.asList(
                        "Venus",
                        "Earth",
                        "Mars",
                        "Jupiter",
                        "Saturn",
                        "Uranus",
                        "Neptune");

        Optional<String> result = planets.stream()
                .filter(planet -> planet.endsWith("o"))
                .findFirst();

        result.ifPresent(System.out::println);

        assertFalse(result.isPresent());
        
        assertEquals("Pluto", result.orElse("Pluto"));
        assertEquals("Pluto", result.orElseGet(() -> "Pluto"));
    }
}
