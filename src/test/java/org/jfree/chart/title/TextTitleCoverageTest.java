package org.jfree.chart.title;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import org.jfree.chart.api.RectangleEdge;
import org.jfree.chart.api.VerticalAlignment;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

public class TextTitleCoverageTest {

    private Graphics2D createGraphics() {
        return new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB).createGraphics();
    }

    @Test
    public void testTargetedStructuralCoverage() {
        TextTitle title = new TextTitle("Targeted Coverage Test");
        Graphics2D g2 = createGraphics();
        Rectangle2D area = new Rectangle2D.Double(0, 0, 150, 150);
        Range r = new Range(0.0, 100.0);

        // --- TARGET 1: arrangeFN (Source Lines 483-521) ---
        // Forces execution of the vertical branch (Lines 500-516)
        title.setPosition(RectangleEdge.LEFT); 
        title.setExpandToFitSpace(true);
        title.arrangeFN(g2, 100.0); // Covers rotation transposition logic (Line 511)
        
        // Forces execution of the horizontal branch (Lines 485-499)
        title.setPosition(RectangleEdge.TOP);  
        title.setExpandToFitSpace(true);
        title.arrangeFN(g2, 100.0); // Covers fixed-width arrangement (Line 493)

        // --- TARGET 2: arrangeRR (Source Lines 554-593) ---
        // Forces execution of the rotated height range branch (Lines 572-588)
        title.setPosition(RectangleEdge.RIGHT);
        title.setExpandToFitSpace(true);
        title.arrangeRR(g2, r, r); // Covers rotated range calculation (Line 583)

        // --- TARGET 3: drawVertical (Source Lines 715-752) ---
        // This also covers 2 lines in parent draw() (Lines 647-649) as the entry gate
        title.setPosition(RectangleEdge.LEFT);
        
        // Populates 'this.content' to bypass null check at Line 621 in draw()
        title.arrange(g2, RectangleConstraint.NONE); 
        
        // Exercise branch for TOP vertical alignment (Line 722)
        title.setVerticalAlignment(VerticalAlignment.TOP);
        title.draw(g2, area);
        
        // Exercise branch for BOTTOM vertical alignment (Line 726)
        title.setVerticalAlignment(VerticalAlignment.BOTTOM);
        title.draw(g2, area);
        
        // Exercise branch for CENTER vertical alignment (Line 730)
        title.setVerticalAlignment(VerticalAlignment.CENTER);
        title.draw(g2, area);
        
        // Final coverage for the RIGHT edge rotation branch (Line 739)
        title.setPosition(RectangleEdge.RIGHT);
        title.draw(g2, area); 
    }
}