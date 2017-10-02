package org.jusecase.scenegraph.node2d;

import org.jusecase.ui.font.Align;

public abstract class Text extends Node2d {
    protected String text;
    protected Align align;

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

    protected abstract void updateLayout();
}
