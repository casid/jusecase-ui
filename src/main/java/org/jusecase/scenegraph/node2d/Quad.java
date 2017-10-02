package org.jusecase.scenegraph.node2d;

import org.jusecase.scenegraph.color.Color;

public class Quad extends Node2d {
    private Color color = new Color(1.0f);

    public Color getColor() {
        return color;
    }

    public Quad setColor(Color color) {
        this.color.set(color);
        return this;
    }

    @Override
    public boolean isRenderable() {
        return true;
    }

    @Override
    public Quad clone() {
        Quad quad = (Quad)super.clone();
        quad.color = color.clone();
        return quad;
    }
}
