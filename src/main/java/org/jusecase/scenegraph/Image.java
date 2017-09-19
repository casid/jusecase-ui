package org.jusecase.scenegraph;

import org.jusecase.scenegraph.texture.Texture;

public class Image extends Quad {
    private final Texture texture;

    public Image(Texture texture) {
        this.texture = texture;
        setSize(texture.getWidth(), texture.getHeight());
    }

    public Texture getTexture() {
        return texture;
    }
}
