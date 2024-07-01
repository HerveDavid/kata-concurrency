package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final Deque<Task> tasks = new ArrayDeque<>();

    private int nbPrepared = 1;
    private static final int MAX_PREPARED = 3;

    private int nbCooked = 1;
    private static final int MAX_COOKED = 5;

    private int nbPacked = 1;
    private static final int MAX_PACKED = 2;

    private int nbCakes = 0;

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        isRunning.set(true);
        while (isRunning.get()) {

            if (nbPrepared <= MAX_PREPARED) {
                prepare();
                nbPrepared++;
            }

            Task task = tasks.pollFirst();

            switch (task.getState()) {
                case Task.State.Completed(Activity activity) -> {
                    switch (activity) {
                        case Prepare -> {
                            if (nbCooked <= MAX_COOKED) {
                                cook();
                                nbCooked++;
                                nbPrepared--;
                            } else {
                                tasks.addLast(task);
                            }
                        }
                        case Cook -> {
                            if (nbPacked <= MAX_PACKED) {
                                pack();
                                nbPacked++;
                                nbCooked--;
                            } else {
                                tasks.addLast(task);
                            }
                        }
                        case Pack -> {
                            nbPacked--;
                            count();
                        }
                    }
                }
                case Task.State.Working() -> tasks.addLast(task);
            }

        }
    }

    public void stop() {
        isRunning.set(false);
    }

    private void prepare() {
        int duration = ThreadLocalRandom.current().nextInt(5, 8 + 1);

        Task task = new Task(Activity.Prepare, Duration.ofSeconds(duration));
        tasks.addLast(task);

        LOGGER.info("Prepare {} activities", nbPrepared);

    }

    private void cook() {
        Task task = new Task(Activity.Cook, Duration.ofSeconds(10));
        tasks.addLast(task);
        LOGGER.info("Cook {} activities", nbCooked);
    }

    private void pack() {
        Task task = new Task(Activity.Pack, Duration.ofSeconds(2));
        tasks.addLast(task);

        LOGGER.info("Pack {} activities", nbPacked);
    }

    private void count() {
        nbCakes++;
        LOGGER.info("Cakes {}", nbCakes);
    }

}