package org.example.model;

import org.apache.commons.lang3.Range;

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

    /**
     * Checks the parameter rectangle for adjacency to this rectangle.
     * It compares the x, y coordinates and each side of the rectangles.
     * If the rectangles share a side, the method returns true.
     * @param anotherRectangle
     * @return
     */
    boolean adjacent(Rectangle anotherRectangle) {
        boolean adjacent = false;

        // get all the lines for this rectangle
        Line[] theseSides = getSides(this);

        // get all sides of other rectangle
        Line[] otherSides = getSides(anotherRectangle);

        for (int i = 0; i < theseSides.length; i++) {
            if (i == 0) { // top side
                Line thisTop = theseSides[i];
                Line otherBottom = otherSides[1];
                Range<Integer> sideRange = Range.between(thisTop.x, thisTop.x2);
                if (sideRange.contains(otherBottom.x) || sideRange.contains(otherBottom.x2) &&
                        thisTop.y == otherBottom.y) {
                    adjacent = true;
                    break;
                }
            } else if (i == 1) { // bottom side
                Line thisBottom = theseSides[i];
                Line otherTop = otherSides[0];
                Range<Integer> sideRange = Range.between(thisBottom.x, thisBottom.x2);
                if (sideRange.contains(otherTop.x) || sideRange.contains(otherTop.x2) &&
                        thisBottom.y == otherTop.y) {
                    adjacent = true;
                    break;
                }
            } else if (i == 2) { // left side
                Line thisLeft = theseSides[i];
                Line otherRight = otherSides[3];
                Range<Integer> sideRange = Range.between(thisLeft.y, thisLeft.y2);
                if (sideRange.contains(otherRight.y) || sideRange.contains(otherRight.y2) &&
                        thisLeft.x == otherRight.x) {
                    adjacent = true;
                    break;
                }
            } else if (i == 3) { // right side
                Line thisRight = theseSides[i];
                Line otherLeft = otherSides[2];
                Range<Integer> sideRange = Range.between(thisRight.y, thisRight.y2);
                if (sideRange.contains(otherLeft.y) || sideRange.contains(otherLeft.y2) &&
                        thisRight.x == otherLeft.x) {
                    adjacent = true;
                    break;
                }
            }
        }

        return adjacent;
    }


    /**
     * Returns a set of 4 sides for the given rectangle.
     * Each side of the rectangle is represented by a Line.
     * The array that is returned is [topSide, bottomSide, leftSide, rightSide]
     * @param rec
     * @return
     */
    private Line[] getSides(Rectangle rec) {
        Line[] sides = new Line[4];
        Line topSide = new Line(rec.x, rec.y, rec.x + rec.width, rec.y);
        Line bottomSide = new Line(rec.x, rec.y + rec.height, rec.x + rec.width, rec.y);
        Line leftSide = new Line(rec.x, rec.y, rec.x, rec.y + rec.height);
        Line rightSide = new Line(rec.x + width, rec.y, rec.x, rec.y + rec.height);

        sides[0] = topSide;
        sides[1] = bottomSide;
        sides[2] = leftSide;
        sides[3] = rightSide;

        return sides;
    }

    /**
     * A class that represents a singular line of a rectangle.
     * int x and y are the starting coordinates of the line, and
     * x2 and y2, are the end coordinates of the line.
     */
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
