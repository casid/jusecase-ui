package org.jusecase.scenegraph.node2d;

import org.jusecase.ui.font.Align;

public abstract class Text extends Node2d {
    protected String text;
    protected Align align = Align.LEFT;

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
}
