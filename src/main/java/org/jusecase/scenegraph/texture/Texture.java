package org.jusecase.scenegraph.texture;

public interface Texture {
    int getId();
    int getWidth();
    int getHeight();
    TexCoords getTexCoords();
    void dispose();
}
