package org.jusecase.scenegraph.tween.properties;

public class FloatProperty implements Property {
    private final float start;
    private final float delta;
    private final FloatConsumer floatConsumer;

    public FloatProperty(float start, float target, FloatConsumer floatConsumer) {
        this.start = start;
        this.delta = target - start;
        this.floatConsumer = floatConsumer;
    }

    @Override
    public void apply(float t) {
        floatConsumer.accept(start + t * delta);
    }

    public interface FloatConsumer {
        void accept(float value);
    }
}
