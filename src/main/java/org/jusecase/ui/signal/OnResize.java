package org.jusecase.ui.signal;

import org.jusecase.signals.Signal;
import org.jusecase.ui.elements.Element;

public class OnResize extends Signal<OnResizeListener> {
    public OnResize() {
        super(OnResizeListener.class);
    }

    public void dispatch(int width, int height) {
        for (int i = 0; i < size; ++i) {
            listeners[i].onResize(width, height);
        }
    }
}
