package org.lebedko.device.monitor.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.lebedko.device.monitor.service.command.ApiCommand;

public class ApiService extends Service {
    private static final String TAG = "ApiService";

    private final ExecutorService pool = Executors.newSingleThreadExecutor();

    private final IBinder mBinder = new ApiBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "CREATED");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "STOPPED");
    }

    public <T extends Serializable> void execute(final ApiCommand<T> command, final Handler handler)  {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Serializable result = command.call();
                    if (Log.isLoggable(TAG, Log.DEBUG)) {
                        Log.d(TAG, "Received data: " + result);
                    }
                    final Message message = Message.obtain();
                    message.getData().putSerializable("result", result);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.d(TAG, "Execute callback in thread " + Thread.currentThread().getName());
                            handler.sendMessage(message);
                        }
                    });
                } catch (Exception e) {
                    Log.e(TAG, "Api exception: " + e.getMessage());
                }
            }
        });
    }

    public class ApiBinder extends Binder {
        public ApiService getService() {
            return ApiService.this;
        }
    }
}
