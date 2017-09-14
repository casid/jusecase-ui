package org.jusecase.ui;

import org.junit.Before;
import org.junit.Test;
import org.jusecase.ui.elements.Element;
import org.jusecase.ui.touch.TouchPhase;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Element_TouchTest extends UiTest {
    Element element1 = new Element();
    Element element2 = new Element();
    Element element2_1 = new Element();
    Element element2_2 = new Element();
    Element element2_2_1 = new Element();

    List<Element> touchedElements = new ArrayList<>();

    @Before
    public void setUp() {
        ui.add(element1.setX(0).setSize(10, 10));
        ui.add(element2.setX(1).setSize(10, 10));
        element2.add(element2_1.setX(1).setSize(10, 10));
        element2.add(element2_2.setX(2).setSize(10, 10));
        element2_2.add(element2_2_1.setX(1).setSize(10, 10));

        ui.visitAll(Element.class, element -> element.onTouch.add(event -> touchedElements.add(element)));
    }

    @Test
    public void element1() {
        givenTouchEvent(0, 0, TouchPhase.Begin);
        whenTouchEventsAreProcessed();
        thenSelectedElementsAre(element1);
    }

    @Test
    public void element2() {
        givenTouchEvent(1, 0, TouchPhase.Begin);
        whenTouchEventsAreProcessed();
        thenSelectedElementsAre(element2);
    }

    @Test
    public void element2_1() {
        givenTouchEvent(2, 0, TouchPhase.Begin);
        whenTouchEventsAreProcessed();
        thenSelectedElementsAre(element2_1);
    }

    @Test
    public void element2_2_1() {
        givenTouchEvent(4, 0, TouchPhase.Begin);
        whenTouchEventsAreProcessed();
        thenSelectedElementsAre(element2_2_1);
    }

    @Test
    public void multitouch() {
        givenTouchEvent(1,3, 0, TouchPhase.Begin);
        givenTouchEvent(2,4, 0, TouchPhase.Begin);

        whenTouchEventsAreProcessed();

        thenSelectedElementsAre(element2_2, element2_2_1);
    }

    @Test
    public void scaled() {
        element2_2_1.setScaleX(-1);
        givenTouchEvent(0, 0, TouchPhase.Begin);

        whenTouchEventsAreProcessed();

        thenSelectedElementsAre(element2_2_1);
    }

    private void thenSelectedElementsAre(Element... elements) {
        assertThat(touchedElements).containsExactly(elements);
    }
}