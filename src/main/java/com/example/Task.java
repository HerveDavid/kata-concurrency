package com.example;

import java.time.Duration;
import java.time.LocalDateTime;

public class Task {

    private final Activity activity;
    private final Duration duration;

    private final LocalDateTime start;

    public Task(Activity activity, Duration duration) {
        this.activity = activity;
        this.duration = duration;
        this.start = LocalDateTime.now();
    }

    public State getState() {
        if (start.isBefore(LocalDateTime.now().minus(duration))) {
            return new State.Completed(activity);
        } else {
            return new State.Working();
        }
    }

    public sealed interface State {
        record Working() implements State {}
        record Completed(Activity activity) implements State {}
    }

}
