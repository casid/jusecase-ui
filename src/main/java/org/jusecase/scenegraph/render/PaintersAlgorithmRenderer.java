package org.jusecase.scenegraph.render;

import org.jusecase.scenegraph.Node;
import org.jusecase.scenegraph.Node2d;

public class PaintersAlgorithmRenderer implements Renderer {
    private final Renderer renderer;

    public PaintersAlgorithmRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void render(Node node) {
        renderer.begin();
        node.visitBottomUp(Node2d.class, n -> {
            if (!n.isVisible()) {
                return true;
            }

            if (n.isRenderable()) {
                renderer.render(n);
            }

            return false;
        });
        renderer.end();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
