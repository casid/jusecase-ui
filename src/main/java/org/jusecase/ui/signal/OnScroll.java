package org.jusecase.ui.signal;

import org.jusecase.signals.Signal;
import org.jusecase.ui.elements.Element;
import org.jusecase.ui.input.ScrollEvent;

public class OnScroll extends Signal<OnScrollListener> {
    public OnScroll() {
        super(OnScrollListener.class);
    }

    public void dispatch(ScrollEvent event) {
        for (int i = 0; i < size; ++i) {
            listeners[i].onScroll(event);
        }
    }
}
