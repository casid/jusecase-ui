package org.jusecase.scenegraph.math;

public interface DrawHashable {
    /**
     * A hash for the renderer to determine if there was a visible change to this instance
     */
    void hash(DrawHash hash);
}
