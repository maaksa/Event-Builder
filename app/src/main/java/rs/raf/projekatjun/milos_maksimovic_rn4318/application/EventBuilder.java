package rs.raf.projekatjun.milos_maksimovic_rn4318.application;

import android.app.Application;

import timber.log.Timber;

public class EventBuilder extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    public void init() {
        initTimber();
    }

    public void initTimber() {
        Timber.plant(new Timber.DebugTree());
    }
}
