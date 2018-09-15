package org.jusecase.ui.signal;

import org.jusecase.ui.input.TouchEvent;

@FunctionalInterface
public interface OnTouchListener {
    void onTouch(TouchEvent touchEvent);
}
