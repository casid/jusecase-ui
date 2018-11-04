package org.jusecase.scenegraph.node2d;

import org.jusecase.scenegraph.math.DrawHash;
import org.jusecase.scenegraph.texture.Texture;

public class Image extends Quad {
    private Texture texture;

    public Image(Texture texture) {
        setTexture(texture);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
        setSize(texture.getWidth(), texture.getHeight());
    }

    public Texture getTexture() {
        return texture;
    }

    @Override
    public void hash(DrawHash hash) {
        super.hash(hash);
        hash.add(texture);
    }
}
