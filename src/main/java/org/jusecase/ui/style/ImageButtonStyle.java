package org.jusecase.ui.style;

import org.jusecase.scenegraph.Image;
import org.jusecase.scenegraph.Quad;
import org.jusecase.scenegraph.color.Color;
import org.jusecase.ui.elements.Button;

public class ImageButtonStyle extends ButtonStyle<Image> {
    public ImageButtonStyle() {
        active = new Image();
        pressed = new Image();
        hovered = new Image();
    }

    @Override
    public void init(Button element) {
        super.init(element);

        if (active != null && active.getTexture() != null) {
            element.setSize(active.getTexture().getWidth(), active.getTexture().getHeight());
        }
    }
}
