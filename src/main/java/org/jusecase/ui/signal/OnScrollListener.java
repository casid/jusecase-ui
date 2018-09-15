package org.jusecase.ui.signal;

import org.jusecase.ui.input.ScrollEvent;

@FunctionalInterface
public interface OnScrollListener {
    void onScroll(ScrollEvent scrollEvent);
}
