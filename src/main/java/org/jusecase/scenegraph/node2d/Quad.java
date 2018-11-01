package org.jusecase.scenegraph.node2d;

import org.jusecase.scenegraph.color.Color;

public class Quad extends Node2d {
    private Color color = Color.WHITE;

    public Color getColor() {
        return color;
    }

    public Quad setColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public boolean isRenderable() {
        return true;
    }
}
