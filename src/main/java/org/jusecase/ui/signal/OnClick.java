package org.jusecase.ui.signal;

import org.jusecase.ui.elements.Element;

@FunctionalInterface
public interface OnClick {
    void onClick(Element element);
}
