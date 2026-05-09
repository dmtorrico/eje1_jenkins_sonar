package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    void testSumar() {
        assertEquals(5, App.sumar(2, 3));
    }
}