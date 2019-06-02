package com.beecoder.dicodingsubmission;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("api/v1/ecall")
    Call<List<Ecall>> getEcallData();
}
