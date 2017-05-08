package org.lebedko.device.monitor;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.lebedko.device.monitor.adapter.AccidentAdapter;
import org.lebedko.device.monitor.service.ApiServiceConnection;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ApiServiceConnection mServiceConnection = new ApiServiceConnection();
    private AccidentAdapter dataAdapter = new AccidentAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);;

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        AccidentListFragment accidentListFragment = new AccidentListFragment();
        fragmentTransaction.replace(R.id.activity_main, accidentListFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
