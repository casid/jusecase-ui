package org.jusecase.ui.touch;

import org.jusecase.ui.elements.Element;

public class TouchEvent implements Cloneable {
    public long id;
    public double x;
    public double y;
    public double deltaX;
    public double deltaY;
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
