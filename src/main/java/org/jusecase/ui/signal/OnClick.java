package org.jusecase.ui.signal;

import org.jusecase.signals.Signal;
import org.jusecase.ui.elements.Element;

public class OnClick extends Signal<OnClickListener> {
    public OnClick() {
        super(OnClickListener.class);
    }

    public void dispatch(Element element) {
        for (int i = 0; i < size; ++i) {
            listeners[i].onClick(element);
        }
    }
}
