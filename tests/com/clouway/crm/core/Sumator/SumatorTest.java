package com.clouway.crm.core.Sumator;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumatorTest {

    Sumator sumator = new Sumator();

    @Test
    void characterIsPassedAsNumber() {
        assertEquals("4", sumator.sum("2", "2"));
    }

    @Test
    void invalidNumberFormatPass(){
        assertThrows(NumberFormatException.class, () -> sumator.sum("a", "b"));
    }

    @Test
    void nullArgumentPass(){
        assertThrows(IllegalArgumentException.class, () -> sumator.sum((String)null, (String)null));
    }
}