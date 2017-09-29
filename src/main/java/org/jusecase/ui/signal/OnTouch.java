package org.jusecase.ui.signal;

import org.jusecase.ui.touch.TouchEvent;

@FunctionalInterface
public interface OnTouch {
    void onTouch(TouchEvent touchEvent);
}
