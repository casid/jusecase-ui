package org.jusecase.scenegraph;

import org.jusecase.scenegraph.texture.Texture;

public class Image extends Quad {
    private Texture texture;

    public Texture getTexture() {
        return texture;
    }

    public Image setTexture(Texture texture) {
        this.texture = texture;
        setSize(texture.getWidth(), texture.getHeight());
        return this;
    }
}
