package org.jusecase.ui.style;

import org.jusecase.scenegraph.Quad;
import org.jusecase.scenegraph.color.Color;

public class QuadButtonStyle extends ButtonStyle<Quad> {
    public QuadButtonStyle() {
        active = new Quad();
        pressed = new Quad();
        hovered = new Quad().setColor(new Color("#eee"));
    }
}
