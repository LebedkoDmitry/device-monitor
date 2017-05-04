package org.lebedko.device.monitor.service.api;

import retrofit2.Call;
import retrofit2.http.GET;
import org.lebedko.device.monitor.dto.RecordsDto;

public interface AccidentApi {
    @GET("/nms-webapp/api/accidents")
    Call<RecordsDto> getData();
}
