package org.jusecase.ui.style;

import org.jusecase.ui.elements.Element;

public abstract class Style<E extends Element> implements Cloneable {
    protected E element;

    public void init(E element) {
        this.element = element;
    }

    public abstract void update();

    @SuppressWarnings("unchecked")
    @Override
    public Style<E> clone() {
        try {
            return (Style<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Most unexpected - clone didn't work", e);
        }
    }
}
