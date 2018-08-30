package org.jusecase.scenegraph.tween.animations;

public class QuadraticInOut implements Animation {
    public static final QuadraticInOut animation = new QuadraticInOut();

    @Override
    public float interpolate(float k) {
        if ((k *= 2.0f) < 1.0f) {
            return 0.5f * k * k;
        }
        return -0.5f * (--k * (k - 2.0f) - 1.0f);
    }
}
