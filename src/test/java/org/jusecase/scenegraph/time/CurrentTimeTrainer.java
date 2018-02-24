package org.jusecase.scenegraph.time;

public class CurrentTimeTrainer extends CurrentTime {

    private long nanos;

    public void givenNanos(long nanos) {
        this.nanos = nanos;
    }

    @Override
    public long getNanos() {
        return nanos;
    }
}