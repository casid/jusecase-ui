package org.jusecase.ui;

import org.jusecase.ui.elements.Element;
import org.jusecase.ui.input.Event;
import org.jusecase.ui.input.ScrollEvent;
import org.jusecase.ui.style.Style;
import org.jusecase.ui.input.TouchEvent;
import org.jusecase.ui.input.TouchPhase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ui extends Element {

    private final Map<Long, WeakReference<Element>> touchedElementsByTouchId = new HashMap<>();
    private final Map<Long, WeakReference<Element>> hoveredElementsByTouchId = new HashMap<>();

    private final Map<Class, Style> styleByClass = new HashMap<>();

    public Ui() {
        setUi(this);
    }

    public void applyDefaultStyle(Element element) {
        if (element.getStyle() == null) {
            Style style = styleByClass.get(element.getClass());
            if (style != null) {
                element.setStyle(style.clone());
            }
        }
    }

    public void process(Event event) {
        if (event instanceof TouchEvent) {
            process((TouchEvent)event);
        } else if (event instanceof ScrollEvent) {
            process((ScrollEvent)event);
        }
    }

    private void process(TouchEvent touchEvent) {
        if (touchEvent.phase == TouchPhase.Hover) {
            Element hoveredElement = getHoveredElement(touchEvent.id);
            visitTopDown(Element.class, element -> processHover(touchEvent, hoveredElement, element));
        } else {
            Element touchedElement = getTouchedElement(touchEvent.id);
            visitTopDown(Element.class, element -> processTouch(touchEvent, touchedElement, element));
        }
    }

    private boolean processTouch(TouchEvent touchEvent, Element touchedElement, Element element) {
        if (element == touchedElement || isElementTouched(element, touchEvent)) {
            if (touchEvent.phase == TouchPhase.Begin) {
                touchedElementsByTouchId.put(touchEvent.id, new WeakReference<>(element));
            }

            if (touchEvent.phase == TouchPhase.End) {
                touchedElementsByTouchId.remove(touchEvent.id);
            }

            touchEvent.element = element;
            element.onTouch.dispatch(touchEvent);
            return touchedElement == null || element == touchedElement;
        }

        return false;
    }

    private boolean processHover(TouchEvent touchEvent, Element hoveredElement, Element element) {
        if (isElementTouched(element, touchEvent)) {
            if (hoveredElement != null) {
                hoveredElement.onHover.dispatch(hoveredElement, false);
            }

            hoveredElementsByTouchId.put(touchEvent.id, new WeakReference<>(element));
            element.onHover.dispatch(element, true);

            return true;
        } else if (element == hoveredElement) {
            hoveredElementsByTouchId.remove(touchEvent.id);
            element.onHover.dispatch(element, false);
        }

        return false;
    }

    private void process(ScrollEvent scrollEvent) {
        Element hoveredElement = getHoveredElement(scrollEvent.id);
        if (hoveredElement != null) {
            scrollEvent.element = hoveredElement;
            hoveredElement.onScroll.dispatch(scrollEvent);
        }
    }

    private boolean isElementTouched(Element element, TouchEvent touchEvent) {
        return element.isTouchable() && element.hitTest(touchEvent.x, touchEvent.y);
    }

    public void process(List<? extends Event> events) {
        events.forEach(this::process);
    }

    List<Element> getTouchedElements() {
        List<Element> result = new ArrayList<>();
        for (WeakReference<Element> reference : touchedElementsByTouchId.values()) {
            Element element = reference.get();
            if (element != null) {
                result.add(element);
            }
        }
        return result;
    }

    private Element getTouchedElement(long touchId) {
        WeakReference<Element> reference = touchedElementsByTouchId.get(touchId);
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    private Element getHoveredElement(long touchId) {
        WeakReference<Element> reference = hoveredElementsByTouchId.get(touchId);
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    public <E extends Element> void setDefaultStyle(Class<E> elementClass, Style style) {
        styleByClass.put(elementClass, style);
    }

    @SuppressWarnings("unchecked")
    public <E extends Element> Style getDefaultStyle(Class<E> elementClass) {
        return styleByClass.get(elementClass);
    }
}
