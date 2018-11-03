package org.jusecase.scenegraph.tween.properties;

import org.jusecase.scenegraph.color.Color;

import java.util.function.Consumer;

public class ColorProperty implements Property {
    private final Color start;
    private final Color delta;
    private final Consumer<Color> colorConsumer;

    public ColorProperty(Color start, Color target, Consumer<Color> colorConsumer) {
        this.start = start;
        this.delta = target.sub(start);
        this.colorConsumer = colorConsumer;
    }

    @Override
    public void apply(float t) {
        colorConsumer.accept(start.lerpDelta(delta, t));
    }
}
