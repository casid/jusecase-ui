package org.jusecase.ui.touch;

import org.jusecase.ui.elements.Element;

public class TouchEvent implements Cloneable {
    public long id;
    public float x;
    public float y;
    public float deltaX;
    public float deltaY;
    public TouchPhase phase;
    public Element element;

    @Override
    public TouchEvent clone() {
        try {
            return (TouchEvent)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "TouchEvent{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", phase=" + phase +
                '}';
    }
}
