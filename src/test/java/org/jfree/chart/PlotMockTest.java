package org.jfree.chart;

import static org.mockito.Mockito.*;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.PlotChangeListener;
import org.jfree.chart.plot.pie.PiePlot;
import org.junit.jupiter.api.Test;

public class PlotMockTest {

    @Test
    public void testPlotChangeNotification() {

        PiePlot plot = new PiePlot();
        
        // Create a Mock listener using Mockito
        PlotChangeListener mockListener = mock(PlotChangeListener.class);
        
        // Register the mock listener with the plot
        plot.addChangeListener(mockListener);
        
        // Trigger a change
        plot.setCircular(false);
        
        // Verify the listener was notified exactly once
        verify(mockListener, times(1)).plotChanged(any(PlotChangeEvent.class));
        
        System.out.println("Mocking Success: plotChanged was called exactly once.");
    }
}