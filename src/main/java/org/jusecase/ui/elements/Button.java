package org.jusecase.ui.elements;

import org.jusecase.scenegraph.node2d.Image;
import org.jusecase.scenegraph.node2d.Quad;
import org.jusecase.signals.Signal;
import org.jusecase.ui.signal.OnClick;
import org.jusecase.ui.style.ButtonStyle;
import org.jusecase.ui.style.Style;
import org.jusecase.ui.touch.TouchEvent;
import org.jusecase.ui.touch.TouchPhase;

public class Button extends Element {
    public final Signal<OnClick> onClick = new Signal<>();

    private boolean pressed;
    private boolean hovered;

    private Quad background;

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
        ButtonStyle style = getStyle();
        if (style != null) {

            if (pressed) {
                applyStyle(style.pressed);
            } else {
                if (hovered) {
                    applyStyle(style.hovered);
                } else {
                    applyStyle(style.active);
                }
            }
        }
    }

    @Override
    public ButtonStyle getStyle() {
        return (ButtonStyle) super.getStyle();
    }

    @Override
    public void setStyle(Style style) {
        if (!(style instanceof ButtonStyle)) {
            throw new IllegalStateException("Unsupported style " + style.getClass());
        }
        super.setStyle(style);

        update();
    }

    private void applyStyle(ButtonStyle.State state) {
        // TODO backgrounds with different textures, texture changes ...
        if (background == null) {
            if (state.texture != null) {
                background = new Image(state.texture);
                setSize(background.getWidth(), background.getHeight());
            } else {
                background = new Quad();
            }
            addFirst(background);
        }

        background.setColor(state.color);
        background.setSize(getWidth(), getHeight());
    }

    private void onHover(boolean started) {
        hovered = started;
        update();
    }

    public boolean isHovered() {
        return hovered;
    }
}
