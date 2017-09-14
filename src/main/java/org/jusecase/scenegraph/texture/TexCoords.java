package org.jusecase.scenegraph.texture;

public class TexCoords {
    public static final TexCoords DEFAULT = new TexCoords(0, 1, 1, 0);

    public final double left;
    public final double bottom;
    public final double right;
    public final double top;


    public TexCoords(double left, double bottom, double right, double top) {
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        this.top = top;
    }
}
