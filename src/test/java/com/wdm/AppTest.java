package com.wdm;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void equalTest() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        assertTrue(c == d); // true
        assertFalse(e == f); // false
        assertTrue(c == (a + b)); // true
        assertTrue(c.equals(a + b)); // true
        assertTrue(g == (a + b)); // true
        assertFalse(g.equals(a + b)); //false
    }
}
