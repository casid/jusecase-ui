package org.jusecase.ui.signal;

import org.jusecase.ui.elements.Element;

@FunctionalInterface
public interface OnHoverListener {
    void onHover(Element element, boolean started);
}
