package org.jusecase.ui;

import org.jusecase.scenegraph.node2d.Node2d;
import org.jusecase.ui.input.Event;
import org.jusecase.ui.input.ScrollEvent;
import org.jusecase.ui.input.TouchEvent;
import org.jusecase.ui.input.TouchPhase;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class UiTest {
    protected Ui ui = new Ui();
    protected List<Event> events = new ArrayList<>();

    protected void givenTouchEvent(float x, float y, TouchPhase phase) {
        givenTouchEvent(0, x, y, phase);
    }

    protected void givenTouchEvent(long id, float x, float y, TouchPhase phase) {
        TouchEvent touchEvent = new TouchEvent();
        touchEvent.id = id;
        touchEvent.x = x;
        touchEvent.y = y;
        touchEvent.phase = phase;
        events.add(touchEvent);
    }

    protected void givenScrollEvent(float deltaX, float deltaY) {
        ScrollEvent scrollEvent = new ScrollEvent();
        scrollEvent.deltaX = deltaX;
        scrollEvent.deltaY = deltaY;
        events.add(scrollEvent);
    }

    protected void whenEventsAreProcessed() {
        ui.process(events);
    }

    protected void thenTouchedElementsAreEmpty() {
        assertThat(ui.getTouchedElements()).isEmpty();
    }

    protected void thenNodeIsAt(Node2d node, float x, float y, float w, float h) {
        assertThat(node.getX()).describedAs("x").isEqualTo(x);
        assertThat(node.getY()).describedAs("y").isEqualTo(y);
        assertThat(node.getWidth()).describedAs("w").isEqualTo(w);
        assertThat(node.getHeight()).describedAs("h").isEqualTo(h);
    }
}
