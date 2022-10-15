package org.example.model

import spock.lang.Specification

class RectangleSpecification extends Specification {

    def "tests whether a rectangle intersects"() {
        given: "a rectangle"
        Rectangle aRectangle = new Rectangle(10, 20, 40, 20)
        when: "the values set fall within the original rectangle"
        Rectangle intersectingRectangle = new Rectangle(10, 10, 20, 40)
        then: "the rectangles intersect"
        aRectangle.intersects(intersectingRectangle)

        when: "the values set fall outside of the original rectangle"
        Rectangle nonIntersectingRectangle = new Rectangle(50, 50, 10, 10)
        then: "the rectangles do not intersect"
        !aRectangle.intersects(nonIntersectingRectangle)
    }
}
