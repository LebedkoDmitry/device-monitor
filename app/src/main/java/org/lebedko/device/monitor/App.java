package org.lebedko.device.monitor;

import android.app.Application;

import org.lebedko.device.monitor.service.ApiBuilder;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ApiBuilder.init(
                getResources().getString(R.string.host),
                getResources().getInteger(R.integer.port)
        );
    }
}
