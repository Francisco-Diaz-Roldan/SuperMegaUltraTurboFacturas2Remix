package com.example.supermegaultraturbofacturas2remix.io.api;

import static com.example.supermegaultraturbofacturas2remix.constantes.Constantes.PALABRA_CLAVE_BUSQUEDA_URL;

import com.example.supermegaultraturbofacturas2remix.io.facturas.FacturasResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FacturasService {

    @GET(PALABRA_CLAVE_BUSQUEDA_URL)
    Call<FacturasResult> getObjetoFacturas();

}
