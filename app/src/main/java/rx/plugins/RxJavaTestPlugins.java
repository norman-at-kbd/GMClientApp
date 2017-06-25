package rx.plugins;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by z01tan on 6/25/17.
 */

public class RxJavaTestPlugins {

    private static Scheduler immediateScheduler;

    private static RxJavaSchedulersHook javaImmediateHook = new RxJavaSchedulersHook() {
        @Override
        public Scheduler getIOScheduler() {
            return getImmediateScheduler();
        }

        @Override
        public Scheduler getComputationScheduler() {
            return getImmediateScheduler();
        }

        @Override
        public Scheduler getNewThreadScheduler() {
            return getImmediateScheduler();
        }
    };

    public static void resetJavaTestPlugins() {
        RxJavaPlugins.getInstance().reset();
    }

    public static void setImmediateScheduler() {
        RxJavaPlugins.getInstance().reset();
        RxJavaPlugins.getInstance().registerSchedulersHook(javaImmediateHook);
    }

    private static Scheduler getImmediateScheduler() {
        if (immediateScheduler == null) {
            immediateScheduler = Schedulers.immediate();
        }
        return immediateScheduler;
    }

}