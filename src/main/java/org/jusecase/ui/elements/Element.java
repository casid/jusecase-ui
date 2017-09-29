package org.jusecase.ui.elements;

import org.jusecase.scenegraph.Node;
import org.jusecase.signals.Signal;
import org.jusecase.ui.Ui;
import org.jusecase.ui.style.Style;
import org.jusecase.ui.signal.OnHover;
import org.jusecase.ui.signal.OnTouch;
import org.jusecase.scenegraph.Node2d;

public class Element extends Node2d {
    public final Signal<OnTouch> onTouch = new Signal<>();
    public final Signal<OnHover> onHover = new Signal<>();

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
}
