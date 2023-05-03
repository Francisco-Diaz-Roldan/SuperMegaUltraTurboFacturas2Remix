package com.example.supermegaultraturbofacturas2remix.io.api;

import com.example.supermegaultraturbofacturas2remix.io.facturas.FacturasVO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FacturasService {

    @GET("README.md")
    Call<FacturasVO> getObjetoFacturas();

}
