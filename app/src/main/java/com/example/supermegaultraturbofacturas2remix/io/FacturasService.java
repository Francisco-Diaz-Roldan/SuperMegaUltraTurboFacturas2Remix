package com.example.supermegaultraturbofacturas2remix.io;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FacturasService {

    @GET("facturas")
    Call<FacturasVO> getObjetoFacturas();

}
