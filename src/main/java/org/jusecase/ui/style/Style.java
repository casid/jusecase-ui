package org.jusecase.ui.style;

public abstract class Style implements Cloneable {

    @SuppressWarnings("unchecked")
    @Override
    public Style clone() {
        try {
            return (Style) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Most unexpected - clone didn't work", e);
        }
    }
}
