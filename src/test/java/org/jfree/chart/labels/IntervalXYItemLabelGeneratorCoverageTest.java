package org.jfree.chart.labels;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.NumberFormat;

import org.jfree.chart.labels.IntervalXYItemLabelGenerator;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYIntervalSeries;
import org.jfree.data.xy.XYIntervalSeriesCollection;
import org.junit.jupiter.api.Test;

public class IntervalXYItemLabelGeneratorCoverageTest {

    // Non-interval + default NumberFormat branch
    @Test
    public void testNonInterval_NumberFormatBranch() {
        XYSeries series = new XYSeries("S1");
        series.add(1.0, 2.0);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        IntervalXYItemLabelGenerator generator =
                new IntervalXYItemLabelGenerator();

        String label = generator.generateLabel(dataset, 0, 0);
        assertNotNull(label);

        // equals true
        assertTrue(generator.equals(generator));

        // equals false
        assertFalse(generator.equals(new Object()));

        // hashCode
        generator.hashCode();
    }

    // Interval dataset + DateFormat branch
    @Test
    public void testInterval_DateFormatBranch() {
        XYIntervalSeries series = new XYIntervalSeries("S2");

        long now = System.currentTimeMillis();
        series.add(now, now - 1000, now + 1000,
                   now, now - 500, now + 500);

        XYIntervalSeriesCollection dataset =
                new XYIntervalSeriesCollection();
        dataset.addSeries(series);

        DateFormat dateFormat = DateFormat.getDateInstance();

        IntervalXYItemLabelGenerator generator =
                new IntervalXYItemLabelGenerator(
                        "{2}", dateFormat, dateFormat);

        String label = generator.generateLabel(dataset, 0, 0);
        assertNotNull(label);
    }

    // NaN + null branches (Y, StartY, EndY)
    @Test
    public void testNaN_NullBranches() {
        XYIntervalSeries series = new XYIntervalSeries("S3");

        // NaN values trigger Double.isNaN checks
        series.add(1.0, 1.0, 1.0,
                   Double.NaN, Double.NaN, Double.NaN);

        XYIntervalSeriesCollection dataset =
                new XYIntervalSeriesCollection();
        dataset.addSeries(series);

        IntervalXYItemLabelGenerator generator =
                new IntervalXYItemLabelGenerator();

        String label = generator.generateLabel(dataset, 0, 0);
        assertNotNull(label);
    }

    // Constructor overload: Date X / Number Y
    @Test
    public void testConstructor_DateX_NumberY() {
        DateFormat xDate = DateFormat.getDateInstance();
        NumberFormat yNumber = NumberFormat.getInstance();

        IntervalXYItemLabelGenerator generator =
                new IntervalXYItemLabelGenerator(
                        "{2}", xDate, yNumber);

        XYSeries series = new XYSeries("S4");
        series.add(System.currentTimeMillis(), 5.0);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        assertNotNull(generator.generateLabel(dataset, 0, 0));
    }

    // Constructor overload: Number X / Date Y
    @Test
    public void testConstructor_NumberX_DateY() {
        NumberFormat xNumber = NumberFormat.getInstance();
        DateFormat yDate = DateFormat.getDateInstance();

        IntervalXYItemLabelGenerator generator =
                new IntervalXYItemLabelGenerator(
                        "{2}", xNumber, yDate);

        XYSeries series = new XYSeries("S5");
        series.add(5.0, System.currentTimeMillis());

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        assertNotNull(generator.generateLabel(dataset, 0, 0));
    }

    // Constructor overload: Date X / Date Y
    @Test
    public void testConstructor_DateX_DateY() {
        DateFormat xDate = DateFormat.getDateInstance();
        DateFormat yDate = DateFormat.getDateInstance();

        IntervalXYItemLabelGenerator generator =
                new IntervalXYItemLabelGenerator(
                        "{2}", xDate, yDate);

        XYSeries series = new XYSeries("S6");
        series.add(System.currentTimeMillis(),
                   System.currentTimeMillis());

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        assertNotNull(generator.generateLabel(dataset, 0, 0));
    }

    // clone()
    @Test
    public void testClone() throws CloneNotSupportedException {
        IntervalXYItemLabelGenerator generator =
                new IntervalXYItemLabelGenerator();

        Object clone = generator.clone();
        assertNotNull(clone);
        assertTrue(clone instanceof IntervalXYItemLabelGenerator);
    }
}
