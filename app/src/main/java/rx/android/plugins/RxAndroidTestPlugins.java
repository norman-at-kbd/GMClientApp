package rx.android.plugins;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by z01tan on 6/25/17.
 */


public class RxAndroidTestPlugins {

    private static RxAndroidSchedulersHook androidRxHook = new RxAndroidSchedulersHook() {
        @Override
        public Scheduler getMainThreadScheduler() {
            return Schedulers.immediate();
        }
    };

    public static void resetAndroidTestPlugins() {
        RxAndroidPlugins.getInstance().reset();
    }

    public static void setImmediateScheduler() {
        RxAndroidPlugins.getInstance().reset();
        RxAndroidPlugins.getInstance().registerSchedulersHook(androidRxHook);
    }

}