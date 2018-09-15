package org.jusecase.ui.signal;

import org.jusecase.signals.Signal;
import org.jusecase.ui.input.ScrollEvent;
import org.jusecase.ui.input.TouchEvent;

public class OnTouch extends Signal<OnTouchListener> {
    public OnTouch() {
        super(OnTouchListener.class);
    }

    public void dispatch(TouchEvent event) {
        for (int i = 0; i < size; ++i) {
            listeners[i].onTouch(event);
        }
    }
}
