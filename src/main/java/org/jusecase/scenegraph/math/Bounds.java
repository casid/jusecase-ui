package org.jusecase.scenegraph.math;

public class Bounds {
    public final float left;
    public final float right;
    public final float top;
    public final float bottom;

    public Bounds(float left, float right, float top, float bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public boolean contains(Bounds bounds) {
        return bounds.left >= left && bounds.right <= right && bounds.top >= top && bounds.bottom <= bottom;
    }
}
