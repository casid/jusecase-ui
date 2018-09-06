package org.jusecase;

import org.jusecase.scenegraph.render.Renderer;
import org.jusecase.ui.input.Event;

public interface Application {

    void init();

    void process(Event event);

    void update();

    void render(Renderer renderer);

    void dispose();
}
