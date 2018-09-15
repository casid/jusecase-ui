package org.jusecase.ui.signal;

import org.jusecase.signals.Signal;
import org.jusecase.ui.elements.Element;

public class OnHover extends Signal<OnHoverListener> {
    public OnHover() {
        super(OnHoverListener.class);
    }

    public void dispatch(Element element, boolean started) {
        for (int i = 0; i < size; ++i) {
            listeners[i].onHover(element, started);
        }
    }
}
