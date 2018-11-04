package org.jusecase.scenegraph.tween.animations;

public class Linear implements Animation {
    public static final Linear animation = new Linear();

    @Override
    public float interpolate(float t) {
        return t;
    }
}
