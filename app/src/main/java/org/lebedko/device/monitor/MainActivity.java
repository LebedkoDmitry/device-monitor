package org.lebedko.device.monitor;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import org.lebedko.device.monitor.adapter.ArticleAdapter;
import org.lebedko.device.monitor.service.ApiServiceConnection;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private ApiServiceConnection mServiceConnection = new ApiServiceConnection();
    private ArticleAdapter dataAdapter = new ArticleAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);;

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        AccidentListFragment articleListFragment = new AccidentListFragment();
        fragmentTransaction.replace(R.id.activity_main, articleListFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
