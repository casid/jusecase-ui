package org.jusecase.ui;

import org.jusecase.ui.touch.TouchEvent;
import org.jusecase.ui.touch.TouchPhase;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class UiTest {
    protected Ui ui = new Ui();
    protected List<TouchEvent> touchEvents = new ArrayList<>();

    protected void givenTouchEvent(double x, double y, TouchPhase phase) {
        givenTouchEvent(0, x, y, phase);
    }

    protected void givenTouchEvent(long id, double x, double y, TouchPhase phase) {
        TouchEvent touchEvent = new TouchEvent();
        touchEvent.id = id;
        touchEvent.x = x;
        touchEvent.y = y;
        touchEvent.phase = phase;
        touchEvents.add(touchEvent);
    }

    protected void whenTouchEventsAreProcessed() {
        ui.process(touchEvents);
    }

    protected void thenTouchedElementsAreEmpty() {
        assertThat(ui.getTouchedElements()).isEmpty();
    }
}
