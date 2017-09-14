package org.jusecase.scenegraph.math;

public class Bounds {
    public final double left;
    public final double right;
    public final double top;
    public final double bottom;

    public Bounds(double left, double right, double top, double bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public boolean contains(Bounds bounds) {
        return bounds.left >= left && bounds.right <= right && bounds.top >= top && bounds.bottom <= bottom;
    }
}
