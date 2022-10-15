package org.example.model

import spock.lang.Specification

class RectangleSpecification extends Specification {
    def "a simple assertion test"() {
        expect:
        1 == 1
    }

    def "tests whether a rectangle intersects"() {
        given: "a rectangle"
        Rectangle aRectangle = new Rectangle();
        when: "another rectangle intersects"
        Rectangle intersectingRectangle = new Rectangle();
        then:
        aRectangle.intersects(intersectingRectangle) == true;
    }
}
