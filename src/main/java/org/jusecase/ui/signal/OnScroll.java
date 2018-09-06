package org.jusecase.ui.signal;

import org.jusecase.ui.input.ScrollEvent;

@FunctionalInterface
public interface OnScroll {
    void onScroll(ScrollEvent scrollEvent);
}
