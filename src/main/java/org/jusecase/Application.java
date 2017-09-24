package org.jusecase;

import org.jusecase.scenegraph.render.Renderer;
import org.jusecase.ui.touch.TouchEvent;

public interface Application {

    void init();

    void process(TouchEvent touchEvent);

    void update();

    void render(Renderer renderer);

    void dispose();

}
