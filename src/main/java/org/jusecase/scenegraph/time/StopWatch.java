package org.jusecase.scenegraph.time;

import org.jusecase.inject.Component;

import javax.inject.Inject;

@Component
public class StopWatch implements Timer {

    @Inject
    private CurrentTime currentTime;

    private long startTimeInNanos;
    private float dt;


    @Override
    public float dt() {
        return dt;
    }

    public void start() {
        startTimeInNanos = currentTime.getNanos();
    }

    public void stop() {
        dt = (currentTime.getNanos() - startTimeInNanos) / 1000000000.0f;
    }
}
