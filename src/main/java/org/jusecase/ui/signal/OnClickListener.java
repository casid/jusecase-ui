package org.jusecase.ui.signal;

import org.jusecase.ui.elements.Element;

@FunctionalInterface
public interface OnClickListener {
    void onClick(Element element);
}
