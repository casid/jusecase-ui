package org.jusecase.ui;

import org.jusecase.ui.elements.Element;
import org.jusecase.ui.style.Style;
import org.jusecase.ui.touch.TouchEvent;
import org.jusecase.ui.touch.TouchPhase;

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
                element.setStyle((Style)style.clone());
            }
        }
    }

    public void process(TouchEvent touchEvent) {
        if (touchEvent.phase == TouchPhase.Hover) {
            Element hoveredElement = getHoveredElement(touchEvent.id);
            visitTopDown(Element.class, element -> processHover(touchEvent, hoveredElement, element));
        } else {
            Element touchedElement = getTouchedElement(touchEvent.id);
            visitTopDown(Element.class, element -> processTouch(touchEvent, touchedElement, element));
        }
    }

    private boolean processTouch(TouchEvent touchEvent, Element touchedElement, Element element) {
        if (element == touchedElement || element.hitTest(touchEvent.x, touchEvent.y)) {
            if (touchEvent.phase == TouchPhase.Begin) {
                touchedElementsByTouchId.put(touchEvent.id, new WeakReference<>(element));
            }

            if (touchEvent.phase == TouchPhase.End) {
                touchedElementsByTouchId.remove(touchEvent.id);
            }

            element.onTouch.dispatch(touchEvent);
            return touchedElement == null || element == touchedElement;
        }

        return false;
    }

    private boolean processHover(TouchEvent touchEvent, Element hoveredElement, Element element) {
        if (element.hitTest(touchEvent.x, touchEvent.y)) {
            if (hoveredElement != null) {
                hoveredElement.onHover.dispatch(false);
            }

            hoveredElementsByTouchId.put(touchEvent.id, new WeakReference<>(element));
            element.onHover.dispatch(true);

            return true;
        } else if (element == hoveredElement) {
            hoveredElementsByTouchId.remove(touchEvent.id);
            element.onHover.dispatch(false);
        }

        return false;
    }


    public void process(List<TouchEvent> touchEvents) {
        touchEvents.forEach(this::process);
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

    public <E extends Element> void setDefaultStyle(Class<E> elementClass, Style<E> style) {
        styleByClass.put(elementClass, style);
    }

    @SuppressWarnings("unchecked")
    public <E extends Element> Style<E> getDefaultStyle(Class<E> elementClass) {
        return styleByClass.get(elementClass);
    }
}
