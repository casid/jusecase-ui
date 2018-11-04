package org.jusecase.scenegraph.node2d;

import org.jusecase.scenegraph.color.Color;
import org.jusecase.scenegraph.math.DrawHash;
import org.jusecase.scenegraph.math.DrawHashable;
import org.jusecase.scenegraph.render.BlendMode;

public class Quad extends Node2d implements DrawHashable {
    private Color color = Color.WHITE;
    private BlendMode blendMode = BlendMode.Default;

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

    public BlendMode getBlendMode() {
        return blendMode;
    }

    public void setBlendMode(BlendMode blendMode) {
        this.blendMode = blendMode;
    }

    @Override
    public void hash(DrawHash hash) {
        hash.add(getGlobalMatrix());
        hash.add(getColor());
    }
}
