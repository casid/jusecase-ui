package org.jusecase.scenegraph.render;

import org.jusecase.scenegraph.Node;

public interface Renderer {
    default void begin() {}
    void render(Node node);
    default void end() {}
}
