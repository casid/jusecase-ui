package org.jusecase.scenegraph.tween;

import org.jusecase.scenegraph.tween.animations.Animation;
import org.jusecase.scenegraph.tween.animations.QuadraticOut;
import org.jusecase.scenegraph.tween.properties.Property;

import java.util.ArrayList;
import java.util.List;

public class Tween {
    private final Object owner;

    private float duration = 1.0f;
    private Animation animation = QuadraticOut.animation;
    private final List<Property> properties = new ArrayList<>();

    private float time;

    Tween(Object owner) {
        this.owner = owner;
    }

    public Tween duration(float duration) {
        this.duration = duration;
        return this;
    }

    public Tween animation(Animation animation) {
        this.animation = animation;
        return this;
    }

    public Tween property(Property property) {
        properties.add(property);
        return this;
    }

    public void update(float dt) {
        time += dt;
        if (time > duration) {
            time = duration;
        }

        float t = animation.interpolate(time / duration);

        for (Property property : properties) {
            property.apply(t);
        }
    }

    public boolean isComplete() {
        return time >= duration;
    }

    public Object getOwner() {
        return owner;
    }
}
