package com.example.supermegaultraturbofacturas2remix.io.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.supermegaultraturbofacturas2remix.R;
import com.example.supermegaultraturbofacturas2remix.io.api.APIAdapter;
import com.example.supermegaultraturbofacturas2remix.io.facturas.FacturaAdapter;
import com.example.supermegaultraturbofacturas2remix.io.facturas.FacturasVO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FacturaAdapter adapter;

    private RecyclerView rv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv1 = findViewById(R.id.rv1);
        rv1.setAdapter(rv1.getAdapter());
        enqueueFacturas();

        //Cambiar nombre de la toolbar del proyecto por el nombre que yo quiera
        MainActivity.this.setTitle("Facturas");


        //Cojo la toolbar creada en el xml y la meto en el codigo
        Toolbar toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
    }

    // TODO añadir el adapter y vincularlo con el recycler view (rv1.setAdapter(adapter))

    private void enqueueFacturas() {
        //Recojo una llamada para el servicio FacturasService
        Call<FacturasVO> facturasCall = APIAdapter.getApiService().getObjetoFacturas();
        facturasCall.enqueue(new Callback<FacturasVO>() {
            @Override
            public void onResponse(Call<FacturasVO> call, Response<FacturasVO> response) {
                if(response.isSuccessful()){
                    FacturasVO facturaObject = response.body();
                    Log.d("onResponse facturas","Tamaño de la factura=>" + facturaObject.getFacturas().size());
                    adapter = new FacturaAdapter(response.body().getFacturas());
                    rv1.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<FacturasVO> call, Throwable t) {

            }
        });
    }
}

