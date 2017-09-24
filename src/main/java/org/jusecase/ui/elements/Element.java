package org.jusecase.ui.elements;

import org.jusecase.scenegraph.Node;
import org.jusecase.signals.Signal;
import org.jusecase.ui.Ui;
import org.jusecase.ui.elements.events.HoverEvent;
import org.jusecase.scenegraph.render.Renderer;
import org.jusecase.ui.style.Style;
import org.jusecase.ui.touch.TouchEvent;
import org.jusecase.scenegraph.Node2d;

public class Element extends Node2d {
    public final OnTouch onTouch = new OnTouch();
    public final OnHover onHover = new OnHover();

    private Ui ui;
    private Style style;


    public Style getStyle() {
        return style;
    }

    @SuppressWarnings("unchecked")
    public void setStyle(Style style) {
        this.style = style.clone();
        this.style.init(this);
        this.style.update();
    }

    @Override
    public void add(Node node, int index) {
        super.add(node, index);

        if (ui != null && node instanceof Element) {
            Element element = (Element) node;
            element.visitAll(Element.class, e -> {
                e.setUi(ui);
                ui.applyDefaultStyle(e);
            });
        }
    }

    @Override
    public void remove(Node node) {
        super.remove(node);
        node.visitAll(Element.class, e -> e.ui = null);
    }

    public Ui getUi() {
        return ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public class OnTouch extends Signal<TouchEvent> {
        public OnTouch() {
            super();
        }

        public void dispatch(TouchEvent event) {
            super.dispatch(event);
        }
    }

    public class OnHover extends Signal<HoverEvent> {
        public OnHover() {
            super();
        }

        public void dispatch(boolean started) {
            super.dispatch(event -> event.started = started);
        }
    }
}
