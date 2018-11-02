package org.jusecase.scenegraph.time;

import org.jusecase.signals.Scheduler;

public class NextFrameScheduler extends Scheduler implements NextFrame {
    @Override
    public void schedule(Runnable runnable) {
        add(runnable);
    }
}
