package org.jusecase.scenegraph.tween.properties;

public class IntProperty implements Property {
    private final int start;
    private final int delta;
    private final IntConsumer intConsumer;

    private int currentValue;

    public IntProperty(int start, int target, IntConsumer intConsumer) {
        this.start = start;
        this.delta = target - start;
        this.intConsumer = intConsumer;

        this.currentValue = start;
    }

    @Override
    public void apply(float t) {
        int newValue = start + (int)(t * delta);
        if (newValue != currentValue) {
            intConsumer.accept(newValue);
            currentValue = newValue;
        }
    }

    public interface IntConsumer {
        void accept(int value);
    }
}
