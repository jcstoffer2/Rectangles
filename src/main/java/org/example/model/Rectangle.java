package org.example.model;

import org.apache.commons.lang3.Range;

import java.time.temporal.ValueRange;

/**
 * A class to represent a rectangle object
 *
 * @Author Jordan Stoffer
 */
public class Rectangle {
    private int x; // top left x coordinate of the rectangle
    private int y; // top left y coordinate of the rectangle
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns true if the parameter anotherRectangle
     * intersects with this Rectangle.
     */
    boolean intersects(Rectangle anotherRectangle) {
        boolean intersects = false;
        int x = anotherRectangle.x;
        int y = anotherRectangle.y;
        int width = anotherRectangle.width;
        int height = anotherRectangle.height;

        if (
                x + width > this.x && // x coordinate is inside this rectangle
                        y + height > this.y && // y coordinate is inside this rectangle
                        x < this.x + this.width &&
                        y < this.y + this.height
        ) {
            intersects = true;
        }

        return intersects;
    }

    /**
     * Returns true if the parameter anotherRectangle
     * is contained within this Rectangle.
     */
    boolean contains(Rectangle anotherRectangle) {
        boolean contains = false;
        int x = anotherRectangle.x;
        int y = anotherRectangle.y;
        int width = anotherRectangle.width;
        int height = anotherRectangle.height;

        if (
                x >= this.x && // x coordinate is within this rectangle
                        y >= this.y && // y is within this rectangle
                        x + width <= this.x + this.width && // rectangle width is smaller than this width
                        y + height <= this.y + this.height // rectangle height is also smaller than this height
        ) {
            contains = true;
        }
        return contains;
    }

    boolean adjacent(Rectangle anotherRectangle) {
        boolean adjacent = false;

        // get all the lines for this rectangle
        Line[] theseSides = getSides(this);

        // get all sides of other rectangle
        Line[] otherSides = getSides(anotherRectangle);

        // loop through each side and see if rectangles are adjacent
        for (Line line1 : theseSides) {
            for (Line line2 : otherSides) {
                Range<Integer> rangeX = Range.between(line1.x, line1.x2);
                Range<Integer> rangeY = Range.between(line1.y, line1.y2);
                if (rangeX.contains(line2.x) || rangeX.contains(line2.y) ||
                        rangeX.contains(line2.x2) || rangeX.contains(line2.y2) ||
                        rangeY.contains(line2.x) || rangeY.contains(line2.y) ||
                        rangeY.contains(line2.y) || rangeY.contains(line2.y2)) {
                    adjacent = true;
                    break;
                }
            }
        }

        return adjacent;
    }

    private Line[] getSides(Rectangle rec) {
        Line[] sides = new Line[4];
        Line topSide = new Line(rec.x, rec.y, x + rec.width, y);
        Line bottomSide = new Line(rec.x, rec.y + rec.height, x + rec.width, y);
        Line leftSide = new Line(rec.x, rec.y, x, y + rec.height);
        Line rightSide = new Line(rec.x + width, rec.y, x, y + rec.height);

        sides[0] = topSide;
        sides[1] = bottomSide;
        sides[2] = leftSide;
        sides[3] = rightSide;

        return sides;
    }

    class Line {
        int x;
        int y;
        int x2;
        int y2;

        public Line(int x, int y, int x2, int y2) {
            this.x = x;
            this.y = y;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}
