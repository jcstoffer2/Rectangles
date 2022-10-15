package org.example.model;

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
}
