package org.jfree.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Functional partition tests for the Range class.
 */
public class NewRangeTest {

    // Partition 1: Valid Symmetric Range
    // Testing length calculation with a standard range spanning zero.
    @Test
    void testValidSymmetricRange() {
        Range r = new Range(-10.0, 10.0);
        assertEquals(20.0, r.getLength(), 0.0000001, "Length should be 20.0");
    }

    // Partition 2: Point Range (Degenerate case)
    // Testing a range where lower and upper bounds are identical.
    @Test
    void testPointRange() {
        Range r = new Range(5.0, 5.0);
        assertEquals(0.0, r.getLength(), 0.0000001, "Length of point range should be 0.0");
    }

    // Partition 3: Invalid Range (Exception Handling)
    // Testing that the constructor rejects invalid bounds (lower > upper).
    @Test
    void testInvalidRangeBounds() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Range(50.0, 10.0);
        }, "Lower bound > upper bound should throw IllegalArgumentException");
    }

    // Partition 4: Range Intersection (Disjoint)
    // Testing two ranges that have no overlapping values.
    @Test
    void testDisjointRangesDoNotIntersect() {
        Range r1 = new Range(0.0, 10.0);
        assertFalse(r1.intersects(20.0, 30.0), "Ranges 0-10 and 20-30 should not intersect");
    }

    // Partition 5: Range Intersection (Overlapping)
    // Testing two ranges that share a portion of their values.
    @Test
    void testOverlappingRangesIntersect() {
        Range r1 = new Range(0.0, 20.0);
        assertTrue(r1.intersects(10.0, 30.0), "Ranges 0-20 and 10-30 should intersect");
    }
}