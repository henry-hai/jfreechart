package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.function.LineFunction2D;
import org.junit.jupiter.api.Test;
import org.jfree.data.general.DatasetUtils;

public class TestableDesignTest {

    @Test
    public void testSampleIntoCollection_StateCheck() {
        // 1. SETUP: Create a real collection instance
        // This was impossible with the original code because it 'new'ed its own.
        XYSeriesCollection<String> collection = new XYSeriesCollection<>();
        LineFunction2D f = new LineFunction2D(0, 1); // f(x) = x

        // 2. EXECUTE: Use the new refactored version
        // We are injecting our 'collection' into the method
        DatasetUtils.sampleIntoCollection(f, 0.0, 10.0, 5, "Series 1", collection);

        // 3. ASSERT: Use standard JUnit to check if it worked
        // We verify the collection we passed in now has the data
        assertEquals(1, collection.getSeriesCount(), "Should have added 1 series");
        assertEquals(5, collection.getSeries(0).getItemCount(), "Should have 5 data points");
        assertEquals("Series 1", collection.getSeriesKey(0), "Series key should match");
    }
}