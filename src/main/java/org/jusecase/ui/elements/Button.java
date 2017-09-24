package org.jusecase.ui.elements;

import org.jusecase.signals.Signal;
import org.jusecase.ui.style.Style;
import org.jusecase.ui.touch.OnClick;
import org.jusecase.ui.touch.TouchEvent;
import org.jusecase.ui.touch.TouchPhase;

public class Button extends Element {
    public final Signal<OnClick> onClick = new Signal<>();

    private boolean pressed;
    private boolean hovered;

    public Button() {
        onTouch.add(this::onTouch);
        onHover.add(this::onHover);
    }

    public boolean isPressed() {
        return pressed;
    }

    private void onTouch(TouchEvent event) {
        if (event.phase == TouchPhase.Begin) {
            pressed = true;
            update();
        } else if (event.phase == TouchPhase.End && pressed) {
            pressed = false;
            update();
            if (hitTest(event.x, event.y)) {
                onClick.dispatch(s -> s.onClick(this));
            }
        }
    }

    private void update() {
        Style style = getStyle();
        if (style != null) {
            style.update();
        }
    }

    private void onHover(boolean started) {
        hovered = started;
        update();
    }

    public boolean isHovered() {
        return hovered;
    }
}
