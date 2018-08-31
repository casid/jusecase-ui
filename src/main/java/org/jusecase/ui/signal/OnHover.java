package org.jusecase.ui.signal;

import org.jusecase.ui.elements.Element;

@FunctionalInterface
public interface OnHover {
    void onHover(Element element, boolean started);
}
