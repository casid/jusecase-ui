package org.jusecase.ui.elements;

import org.jusecase.scenegraph.node2d.Image;
import org.jusecase.scenegraph.node2d.Node2d;
import org.jusecase.scenegraph.node2d.Quad;
import org.jusecase.signals.Signal;
import org.jusecase.ui.font.Align;
import org.jusecase.ui.signal.OnClick;
import org.jusecase.ui.signal.OnClickListener;
import org.jusecase.ui.signal.OnHoverListener;
import org.jusecase.ui.signal.OnTouchListener;
import org.jusecase.ui.style.ButtonStyle;
import org.jusecase.ui.style.Style;
import org.jusecase.ui.input.TouchEvent;
import org.jusecase.ui.input.TouchPhase;

public class Button extends Element implements OnTouchListener, OnHoverListener {
    public final OnClick onClick = new OnClick();

    private boolean pressed;
    private boolean hovered;

    private Quad background;
    private Label label;

    public Button() {
        onTouch.add(this);
        onHover.add(this);
    }

    public boolean isPressed() {
        return pressed;
    }

    @Override
    public void onTouch(TouchEvent event) {
        if (event.phase == TouchPhase.Begin) {
            pressed = true;
            update();
        } else if (event.phase == TouchPhase.End && pressed) {
            pressed = false;
            update();
            if (hitTest(event.x, event.y)) {
                onClick.dispatch(this);
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

    @Override
    public void onHover(Element element, boolean started) {
        hovered = started;
        update();
    }

    public boolean isHovered() {
        return hovered;
    }

    public void setText(String text) {
        if (label == null) {
            label = new Label(getStyle().active.font);
            label.setTouchable(false);
            label.setAlign(Align.CENTER);
            label.setVerticalAlign(0.5f);
            label.setWidth(getWidth());
            label.setHeight(getHeight());

            add(label);
        }

        label.setText(text);
    }

    @Override
    public Node2d setWidth(float width) {
        super.setWidth(width);

        if (background != null) {
            background.setWidth(width);
        }

        if (label != null) {
            label.setWidth(width);
        }

        return this;
    }

    @Override
    public Node2d setHeight(float height) {
        super.setHeight(height);

        if (background != null) {
            background.setHeight(height);
        }

        if (label != null) {
            label.setHeight(height);
        }

        return this;
    }
}
