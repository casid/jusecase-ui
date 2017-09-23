package org.jusecase.scenegraph.texture;

public class TexCoords {
    public static final TexCoords DEFAULT = new TexCoords(0, 1, 1, 0);

    public final float left;
    public final float bottom;
    public final float right;
    public final float top;


    public TexCoords(float left, float bottom, float right, float top) {
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        this.top = top;
    }
}
