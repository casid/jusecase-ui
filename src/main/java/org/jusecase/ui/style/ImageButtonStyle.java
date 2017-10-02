package org.jusecase.ui.style;

import org.jusecase.scenegraph.node2d.Image;
import org.jusecase.ui.elements.Button;

public class ImageButtonStyle extends ButtonStyle<Image> {
    @Override
    public void init(Button element) {
        super.init(element);

        if (active != null && active.getTexture() != null) {
            element.setSize(active.getTexture().getWidth(), active.getTexture().getHeight());
        }
    }
}
