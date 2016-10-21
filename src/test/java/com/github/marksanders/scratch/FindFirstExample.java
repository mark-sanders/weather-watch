package com.github.marksanders.scratch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.junit.Test;

public class FindFirstExample {

    @Test
    public void findFirstExample1a() {
        List<String> planets = 
                Arrays.asList("Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune");

        Optional<String> result = planets.stream()
                .filter(planet -> planet.endsWith("une"))
                .findFirst();
        
        assertTrue(result.isPresent());
        assertEquals("Neptune", result.get());
    }

    @Test
    public void findFirstExample1b() {
        List<String> planets = 
                Arrays.asList("Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune");
        
        Optional<String> result = planets.stream()
                .filter(planet -> planet.endsWith("une"))
                .findFirst();
        
        if (result.isPresent()) {
            System.out.println(result.get());
        }
        
        
        
        result.ifPresent(System.out::println);
    }

    @Test
    public void findFirstExample2() {
        List<String> planets = 
                Arrays.asList("Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune");

        Optional<String> result = planets.stream()
                .filter(planet -> planet.endsWith("o"))
                .findFirst();

        result.ifPresent(System.out::println);

        assertFalse(result.isPresent());
        
        assertEquals("Pluto", result.orElse("Pluto"));
        assertEquals("Pluto", result.orElseGet(() -> "Pluto"));
    }

    @Test
    public void findFirstExample3() {
        List<String> planets = 
                Arrays.asList("Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune");
        
        Optional<String> result = planets.stream()
                .filter(planet -> planet.startsWith("S"))
                .findFirst()
                .map(String::toUpperCase);
        
        assertEquals("SATURN", result.get());
    }
    
    @Test
    public void findFirstExample4() {
        
        List<String> planets = 
                Arrays.asList("Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune");
        
        // dummy function returning an Optional 
        Function<String, Optional<String>> mangle = (name) -> Optional.of(name.substring(2));
        
        Optional<Optional<String>> result = planets.stream()
                .filter(planet -> planet.startsWith("S"))
                .findFirst()
                .map(mangle);
        
        assertEquals("turn", result.get().get());
    }

    @Test
    public void findFirstExample5() {
        
        List<String> planets = 
                Arrays.asList("Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune");
        
        Function<String, Optional<String>> mangle = (name) -> Optional.of(name.substring(2));
        
        Optional<String> result = planets.stream()
                .filter(planet -> planet.startsWith("S"))
                .findFirst()
                .flatMap(mangle);
        
        assertEquals("turn", result.get());
    }
}
