package org.jusecase;

import org.junit.Test;
import org.jusecase.scenegraph.render.Renderer;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {

    DummyApplication application = new DummyApplication();

    @Test
    public void name() {
        // TODO
    }

    private static class DummyApplication extends Application {

        @Override
        protected Renderer createRenderer() {
            return null;
        }

        @Override
        protected void onStart() {

        }

        @Override
        protected void onUpdate() {

        }
    }
}