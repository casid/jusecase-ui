package org.jusecase.ui.input;

import org.jusecase.ui.elements.Element;

public class ScrollEvent extends Event {
    public long id;
    public float deltaX;
    public float deltaY;
    public Element element;

    @Override
    public String toString() {
        return "ScrollEvent{" +
                "id=" + id +
                ", deltaX=" + deltaX +
                ", deltaY=" + deltaY +
                '}';
    }

    public ScrollEvent copyTo(ScrollEvent event) {
        event.deltaX = deltaX;
        event.deltaY = deltaY;
        event.element = element;
        return event;
    }
}
