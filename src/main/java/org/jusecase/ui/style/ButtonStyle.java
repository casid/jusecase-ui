package org.jusecase.ui.style;

import org.jusecase.scenegraph.Node2d;
import org.jusecase.ui.elements.Button;

public abstract class ButtonStyle<N extends Node2d> extends Style<Button> {
    public N active;
    public N pressed;
    public N hovered;

    private N current;

    @Override
    public void update() {
        if (element.isPressed()) {
            setCurrent(pressed);
        } else {
            if (element.isHovered()) {
                setCurrent(hovered);
            } else {
                setCurrent(active);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public ButtonStyle<N> clone() {
        ButtonStyle<N> clone = (ButtonStyle<N>) super.clone();
        clone.active = (N)active.clone();
        clone.pressed = (N)pressed.clone();
        clone.hovered = (N)hovered.clone();

        return clone;
    }

    private void setCurrent(N skin) {
        if (skin != current) {
            if (current != null) {
                current.removeFromParent();
            }

            current = skin;

            if (current != null) {
                current.setWidth(element.getWidth());
                current.setHeight(element.getHeight());
                element.addFirst(current);
            }
        }
    }
}
