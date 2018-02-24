package org.jusecase.scenegraph.time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jusecase.inject.ComponentTest;
import org.jusecase.inject.Trainer;

import static org.assertj.core.api.Assertions.assertThat;

class StopWatchTest implements ComponentTest {
    @Trainer
    CurrentTimeTrainer currentTimeTrainer;

    StopWatch stopWatch;

    @BeforeEach
    void setUp() {
        stopWatch = new StopWatch();
    }

    @Test
    void noTimePassed() {
        whenWatchIsStartedAndStopped(0, 0);
        assertThat(stopWatch.dt()).isEqualTo(0.0f);
    }

    @Test
    void oneSecondPassed() {
        whenWatchIsStartedAndStopped(0, 1000000000L);
        assertThat(stopWatch.dt()).isEqualTo(1.0f);
    }

    @Test
    void oneSecondPassedAgain() {
        whenWatchIsStartedAndStopped(1000000000L, 2000000000L);
        assertThat(stopWatch.dt()).isEqualTo(1.0f);
    }

    @Test
    void fewMillisPassed() {
        whenWatchIsStartedAndStopped(1000000000L, 1500000000L);
        assertThat(stopWatch.dt()).isEqualTo(0.5f);
    }

    private void whenWatchIsStartedAndStopped(long nanosBefore, long nanosAfter) {
        currentTimeTrainer.givenNanos(nanosBefore);
        stopWatch.start();
        currentTimeTrainer.givenNanos(nanosAfter);
        stopWatch.stop();
    }
}