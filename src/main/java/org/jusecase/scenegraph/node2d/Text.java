package org.jusecase.scenegraph.node2d;

import org.jusecase.scenegraph.color.Color;
import org.jusecase.ui.font.Align;

public abstract class Text extends Node2d {
    protected String text;
    protected Align align = Align.LEFT;
    protected Color color = Color.WHITE;

    public void setText(String text) {
        this.text = text;
        updateLayout();
    }

    public String getText() {
        return text;
    }

    public void setAlign(Align align) {
        this.align = align;
        updateLayout();
    }

    public Align getAlign() {
        return align;
    }

    public abstract float getLineHeight();

    protected abstract void updateLayout();

    public void setColor(Color color) {
        this.color = color;
        updateLayout();
    }

    public Color getColor() {
        return color;
    }
}
