package org.jusecase;

import org.jusecase.signals.Signal;
import org.jusecase.ui.signal.OnResize;

public interface ApplicationBackend {
    void exit();

    int getWidth();
    int getHeight();

    Signal<OnResize> onResize();
}
