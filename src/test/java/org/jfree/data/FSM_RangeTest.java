package org.jfree.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FSM_RangeTest {

    @Test
    void testStandardToShiftedState() {
        Range base = new Range(0.0, 10.0);
        Range shifted = Range.shift(base, 5.0); 
        assertEquals(5.0, shifted.getLowerBound());
        assertEquals(15.0, shifted.getUpperBound());
    }

    @Test
    void testStandardToExpandedState() {
        Range base = new Range(10.0, 20.0);
        Range expanded = Range.expand(base, 0.1, 0.1); 
        assertEquals(9.0, expanded.getLowerBound());
        assertEquals(21.0, expanded.getUpperBound());
    }

    @Test
    void testStandardToPointState() {
        Range point = new Range(5.0, 5.0);
        assertEquals(0.0, point.getLength());
    }
}