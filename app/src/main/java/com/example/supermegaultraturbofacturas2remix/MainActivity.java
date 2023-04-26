package com.example.supermegaultraturbofacturas2remix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.supermegaultraturbofacturas2remix.io.FacturasAdapter;
import com.example.supermegaultraturbofacturas2remix.io.FacturasService;
import com.example.supermegaultraturbofacturas2remix.io.FacturasVO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enqueueFacturas();


    }

    private void enqueueFacturas() {
        //Recojo una llamada para el servicio FacturasService
        Call<FacturasVO> facturasCall = FacturasAdapter.getApiService().getObjetoFacturas();
        facturasCall.enqueue(new Callback<FacturasVO>() {
            @Override
            public void onResponse(Call<FacturasVO> call, Response<FacturasVO> response) {
                if(response.isSuccessful()){
                    FacturasVO facturaObject = response.body();
                    Log.d("onResponse facturas","Tamaño de la factura=>" + facturaObject.getFacturas().size());
                }

            }

            @Override
            public void onFailure(Call<FacturasVO> call, Throwable t) {

            }
        });
    }
}