package org.jusecase.scenegraph.texture;

import org.jusecase.scenegraph.math.DrawHashable;

public interface Texture extends DrawHashable {
    int getId();
    int getWidth();
    int getHeight();
    TexCoords getTexCoords();
    TextureFrame getFrame();
    void dispose();
}
