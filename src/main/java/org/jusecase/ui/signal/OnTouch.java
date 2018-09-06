package org.jusecase.ui.signal;

import org.jusecase.ui.input.TouchEvent;

@FunctionalInterface
public interface OnTouch {
    void onTouch(TouchEvent touchEvent);
}
