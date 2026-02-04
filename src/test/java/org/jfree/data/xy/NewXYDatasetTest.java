package org.jfree.data.xy;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * New tests for {@link DefaultXYDataset}.
 */
public class NewXYDatasetTest {

    // Empty dataset partition: no points
    @Test
    void testEmptyDataset() {
        XYSeries series = new XYSeries("Series1");
        XYSeriesCollection dataset = new XYSeriesCollection(series); // empty
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Empty Dataset Chart", "X-Axis", "Y-Axis", dataset);
        assertNotNull(chart);
    }

    // Single-point dataset partition: exactly one point
    @Test
    void testSinglePointDataset() {
        XYSeries series = new XYSeries("Series1");
        series.add(1, 10);
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Single Point Chart", "X-Axis", "Y-Axis", dataset);
        assertNotNull(chart);
    }

    // Small dataset partition: 2–10 points
    @Test
    void testSmallDataset() {
        XYSeries series = new XYSeries("Series1");
        for (int i = 1; i <= 5; i++) {
            series.add(i, i * 10);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Small Dataset Chart", "X-Axis", "Y-Axis", dataset);
        assertNotNull(chart);
    }

    // Medium dataset partition: 11–100 points
    @Test
    void testMediumDataset() {
        XYSeries series = new XYSeries("Series1");
        for (int i = 1; i <= 50; i++) {
            series.add(i, i * 10);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Medium Dataset Chart", "X-Axis", "Y-Axis", dataset);
        assertNotNull(chart);
    }

    // Large dataset partition: more than 100 points
    @Test
    void testLargeDataset() {
        XYSeries series = new XYSeries("Series1");
        for (int i = 1; i <= 200; i++) {
            series.add(i, i * 10);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Large Dataset Chart", "X-Axis", "Y-Axis", dataset);
        assertNotNull(chart);
    }

    // Negative or zero values dataset partition
    @Test
    void testNegativeZeroDataset() {
        XYSeries series = new XYSeries("Series1");
        series.add(-5, -10);
        series.add(0, 0);
        series.add(5, 10);
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Negative/Zero Values Chart", "X-Axis", "Y-Axis", dataset);
        assertNotNull(chart);
    }
}
