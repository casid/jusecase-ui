package org.jusecase.scenegraph.tween.animations;

public class QuadraticIn implements Animation {
    public static final QuadraticIn animation = new QuadraticIn();

    @Override
    public float interpolate(float t) {
        return t * t;
    }
}
