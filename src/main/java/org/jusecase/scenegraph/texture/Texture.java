package org.jusecase.scenegraph.texture;

public interface Texture {
    int getId();
    int getWidth();
    int getHeight();
    TexCoords getTexCoords();
    TextureFrame getFrame();
    void dispose();
}
