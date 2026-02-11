package org.jfree.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FSM_RangeTest {

    @Test
    void testStandardToShiftedState() {
        // FSM: Initial state is Standard
        Range base = new Range(0.0, 10.0);
        // Transition: shift() moves state to Shifted
        Range shifted = Range.shift(base, 5.0); 
        assertEquals(5.0, shifted.getLowerBound(), "Lower bound should shift to 5.0");
        assertEquals(15.0, shifted.getUpperBound(), "Upper bound should shift to 15.0");
    }

    @Test
    void testStandardToExpandedState() {
        // FSM: Initial state is Standard
        Range base = new Range(10.0, 20.0);
        // Transition: expand() moves state to Expanded (10% margin each side)
        Range expanded = Range.expand(base, 0.1, 0.1); 
        assertEquals(9.0, expanded.getLowerBound(), "Lower bound should expand to 9.0");
        assertEquals(21.0, expanded.getUpperBound(), "Upper bound should expand to 21.0");
    }

    @Test
    void testStandardToPointState() {
        // FSM: Transitioning from a range to a single point (Degenerate state)
        Range point = new Range(5.0, 5.0);
        assertEquals(point.getLowerBound(), point.getUpperBound(), "Bounds must be equal for Point state");
        assertEquals(0.0, point.getLength(), "Length of a Point state range must be 0.0");
    }

    @Test
    void testShiftedLoopState() {
        // FSM: Shifted -> Shifted (Loop)
        Range base = new Range(0.0, 10.0);
        Range shift1 = Range.shift(base, 5.0); // First Shift
        Range shift2 = Range.shift(shift1, 5.0); // Loop back into Shifted
        assertEquals(10.0, shift2.getLowerBound());
        assertEquals(20.0, shift2.getUpperBound());
    }
}