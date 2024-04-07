package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToTextConverterTest {

    @Test
    void testZero() throws Exception {
        ToTextConverter converter = new ToTextConverter();
        assertEquals("zero", converter.toText(0));
    }

    @Test
    void testOne() throws Exception {
        ToTextConverter converter = new ToTextConverter();
        assertEquals("jeden", converter.toText(1));
    }

    @Test
    void testTwo() throws Exception {
        ToTextConverter converter = new ToTextConverter();
        assertEquals("dwa", converter.toText(2));
    }

    @Test
    void testThree() throws Exception {
        ToTextConverter converter = new ToTextConverter();
        assertEquals("trzay", converter.toText(3));
    }

    @Test
    void testOutOfRange() {
        ToTextConverter converter = new ToTextConverter();
        assertThrows(Exception.class, () -> converter.toText(5));
    }
}
