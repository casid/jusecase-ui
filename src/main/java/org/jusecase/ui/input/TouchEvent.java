package org.jusecase.ui.input;

import org.jusecase.ui.elements.Element;

public class TouchEvent extends Event {
    public long id;
    public float x;
    public float y;
    public float deltaX;
    public float deltaY;
    public TouchPhase phase;
    public Element element;

    @Override
    public String toString() {
        return "TouchEvent{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", phase=" + phase +
                '}';
    }

    public TouchEvent copyTo(TouchEvent event) {
        event.id = id;
        event.x = x;
        event.y = y;
        event.deltaX = deltaX;
        event.deltaY = deltaY;
        event.phase = phase;
        event.element = element;
        return event;
    }
}
