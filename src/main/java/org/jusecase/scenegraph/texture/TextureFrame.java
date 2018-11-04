package org.jusecase.scenegraph.texture;

import org.jusecase.scenegraph.math.DrawHash;
import org.jusecase.scenegraph.math.DrawHashable;

public class TextureFrame implements DrawHashable {
    public final int left;
    public final int bottom;
    public final int right;
    public final int top;


    public TextureFrame(int left, int bottom, int right, int top) {
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
