package org.jusecase.ui.elements;

import org.jusecase.signals.Signal;
import org.jusecase.ui.elements.events.ButtonClickEvent;
import org.jusecase.ui.elements.events.HoverEvent;
import org.jusecase.ui.style.Style;
import org.jusecase.ui.touch.TouchEvent;
import org.jusecase.ui.touch.TouchPhase;

public class Button extends Element {
    public final OnClick onClick = new OnClick();

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
                onClick.dispatch();
            }
        }
    }

    private void update() {
        Style style = getStyle();
        if (style != null) {
            style.update();
        }
    }

    private void onHover(HoverEvent event) {
        hovered = event.started;
        update();
    }

    public boolean isHovered() {
        return hovered;
    }

    public class OnClick extends Signal<ButtonClickEvent> {
        public OnClick() {
            super();
        }

        void dispatch() {
            dispatch(e -> e.button = Button.this);
        }
    }
}
