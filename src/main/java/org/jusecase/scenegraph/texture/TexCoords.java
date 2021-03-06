package org.jusecase.scenegraph.texture;

import org.jusecase.scenegraph.math.DrawHash;
import org.jusecase.scenegraph.math.DrawHashable;

public class TexCoords implements DrawHashable {
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

    @Override
    public void hash(DrawHash hash) {
        hash.add(left);
        hash.add(bottom);
        hash.add(right);
        hash.add(top);
    }
}
