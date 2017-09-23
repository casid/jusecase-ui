package org.jusecase;

import org.jusecase.scenegraph.render.Renderer;
import org.jusecase.ui.Ui;
import org.jusecase.ui.touch.TouchEvent;


// TODO that's too simple
public abstract class Application {
    private Renderer renderer;
    protected Ui ui = new Ui();


    public void start() {
        renderer = createRenderer();
    }

    protected void render() {
        renderer.render(ui);
    }

    protected void dispose() {
        renderer.dispose();
    }

    protected void processTouch(TouchEvent event) {
        ui.process(event);
    }

    protected abstract Renderer createRenderer();

    protected abstract void onStart();

    protected abstract void onUpdate();
}
