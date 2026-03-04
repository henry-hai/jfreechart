package org.jfree.data.xy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class XYBarDatasetMockTest {

    @Test
    public void testGetSeriesCount_BehaviorCheckWithMock() {
        
        // Create a Mock dataset using Mockito
        XYDataset<String> mockUnderlying = mock(XYDataset.class);

        // Tell the mock how to behave when getSeriesCount() is called
        when(mockUnderlying.getSeriesCount()).thenReturn(3);

        // Register the mock dataset with the wrapper class
        XYBarDataset<String> barDataset = new XYBarDataset<>(mockUnderlying, 1.0);

        // Trigger a change / execute the method
        int result = barDataset.getSeriesCount();

        // Verify the underlying mock was interacted with exactly once
        verify(mockUnderlying, times(1)).getSeriesCount();

        // Verify the wrapper correctly returned the mock's value
        assertEquals(3, result, "The wrapper should return the value provided by the mock");

        System.out.println("Mocking Success: getSeriesCount was called exactly once on the mock underlying dataset.");
    }
}