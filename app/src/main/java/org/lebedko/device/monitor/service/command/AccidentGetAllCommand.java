package org.lebedko.device.monitor.service.command;


import android.util.Log;

import java.util.ArrayList;

import retrofit2.Response;
import org.lebedko.device.monitor.dto.Accident;
import org.lebedko.device.monitor.dto.RecordsDto;
import org.lebedko.device.monitor.service.ApiBuilder;

public class AccidentGetAllCommand extends BaseApiCommand<ArrayList<Accident>> {

    private static final String TAG = "AccidentGetAllCommand";

    @Override
    public ArrayList<Accident> call() throws Exception {
        Log.i(TAG, "call");
        Response<RecordsDto> response = ApiBuilder.getInstance().articleApi.getData().execute();
        Log.i(TAG, "callAfterResponce");
        if (response.isSuccessful()) {
            return new ArrayList<>(response.body().getRecords());
        } else {
            throw new RuntimeException(response.code() + ": " + response.message());
        }
    }
}



