package org.jusecase.scenegraph.tween;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tweens {
    private final List<Tween> tweens = new ArrayList<>();

    public Tween tween() {
        return tween(null);
    }

    public Tween tween(Object owner) {
        remove(owner);

        Tween tween = new Tween(owner);
        tweens.add(tween);
        return tween;
    }

    public void update(float dt) {
        if (tweens.isEmpty()) {
            return;
        }

        Iterator<Tween> iterator = tweens.iterator();
        while (iterator.hasNext()) {
            Tween tween = iterator.next();
            tween.update(dt);

            if (tween.isComplete()) {
                iterator.remove();
            }
        }
    }

    @SuppressWarnings("Java8CollectionRemoveIf")
    private void remove(Object owner) {
        if (tweens.isEmpty()) {
            return;
        }

        Iterator<Tween> iterator = tweens.iterator();
        while (iterator.hasNext()) {
            Tween tween = iterator.next();
            if (tween.getOwner() == owner) {
                iterator.remove();
            }
        }
    }
}
