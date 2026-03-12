package org.jfree.data.xy;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FSM_XYSeriesTest {

    @Test
    void testInitialEmptyState() {
        // FSM: Initial state is Empty
        XYSeries series = new XYSeries("TestSeries");
        
        assertEquals(0, series.getItemCount(), "Series should be in Empty state (size 0)");
        
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Empty Chart", "X", "Y", dataset);
        assertNotNull(chart);
    }

    @Test
    void testEmptyToFillingTransition() {
        // FSM: Empty -> Filling (adding one point)
        XYSeries series = new XYSeries("TestSeries");
        series.setMaximumItemCount(3); // Set constraint
        
        series.add(1.0, 10.0); 
        
        assertEquals(1, series.getItemCount(), "Series should transition to Filling state");
    }

    @Test
    void testFillingToFillingTransition() {
        // FSM: Filling -> Filling (adding points without hitting max)
        XYSeries series = new XYSeries("TestSeries");
        series.setMaximumItemCount(5); 
        
        series.add(1.0, 10.0);
        series.add(2.0, 20.0);
        
        assertEquals(2, series.getItemCount(), "Series should remain in Filling state");
    }

    @Test
    void testFillingToFullTransition() {
        // FSM: Filling -> Full (hitting the capacity limit)
        XYSeries series = new XYSeries("TestSeries");
        series.setMaximumItemCount(2); 
        
        series.add(1.0, 10.0); // Size 1 (Filling)
        series.add(2.0, 20.0); // Size 2 (Full)
        
        assertEquals(2, series.getItemCount(), "Series should now be in the Full state");
    }

    @Test
    void testFullToFullEvictionTransition() {
        // FSM: Full -> Full (Non-trivial eviction logic)
        XYSeries series = new XYSeries("TestSeries");
        series.setMaximumItemCount(2); 
        
        series.add(1.0, 10.0); 
        series.add(2.0, 20.0); // State is now Full
        
        // This addition should trigger the eviction of the oldest item (1.0, 10.0)
        series.add(3.0, 30.0); 
        
        assertEquals(2, series.getItemCount(), "Size should remain at Max Capacity (2)");
        assertEquals(2.0, series.getX(0).doubleValue(), "Oldest item should be evicted, making 2.0 the new first item");
        assertEquals(3.0, series.getX(1).doubleValue(), "Newest item 3.0 should be at the end");
    }

    @Test
    void testFullToFillingTransition() {
        // FSM: Full -> Filling (Removing an item opens up capacity)
        XYSeries series = new XYSeries("TestSeries");
        series.setMaximumItemCount(2); 
        series.add(1.0, 10.0); 
        series.add(2.0, 20.0); // State is now Full
        
        series.remove(0); // Transition back to Filling
        
        assertEquals(1, series.getItemCount(), "Series should transition back to Filling state");
    }

    @Test
    void testRemoveLastItemBecomesEmpty() {
        // FSM: Filling -> Empty (remove last remaining item)
        XYSeries series = new XYSeries("TestSeries");
        series.add(1.0, 10.0); // Filling with 1 item

        series.remove(0); 

        assertEquals(0, series.getItemCount(), "Series should transition to Empty state");
    }

    @Test
    void testClearFromFullState() {
        // FSM: Full -> Empty (clearing the series)
        XYSeries series = new XYSeries("TestSeries");
        series.setMaximumItemCount(2); 
        series.add(1.0, 10.0); 
        series.add(2.0, 20.0); // State is now Full
        
        series.clear(); 
        
        assertEquals(0, series.getItemCount(), "Series should transition from Full directly to Empty");
    }
}