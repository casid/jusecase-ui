package org.jusecase.scenegraph.node2d;

import org.jusecase.scenegraph.texture.Texture;
import org.jusecase.scenegraph.texture.TextureAtlas;

public class Image3Slice extends Node2d {
    private final Image left;
    private final Image center;
    private final Image right;

    public Image3Slice(TextureAtlas atlas, String left, String center, String right) {
        this(atlas.get(left), atlas.get(center), atlas.get(right));
    }

    public Image3Slice(Texture left, Texture center, Texture right) {
        add(this.left = new Image(left));
        add(this.center = new Image(center));
        add(this.right = new Image(right));

        setSize(left.getWidth() + center.getWidth() + right.getWidth(), center.getHeight());
    }

    @Override
    public Node2d setWidth(float width) {
        super.setWidth(width);
        center.setX(left.getWidth()).setWidth(getWidth() - left.getWidth() - right.getWidth());
        right.setX(center.getX() + center.getWidth());
        return this;
    }

    @Override
    public Node2d setHeight(float height) {
        super.setHeight(height);
        left.setHeight(height);
        center.setHeight(height);
        right.setHeight(height);
        return this;
    }
}
