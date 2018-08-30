package org.jusecase.scenegraph.tween.animations;

public class QuadraticOut implements Animation {
    public static final QuadraticOut animation = new QuadraticOut();

    @Override
    public float interpolate(float t) {
        return -t * (t - 2.0f);
    }
}
