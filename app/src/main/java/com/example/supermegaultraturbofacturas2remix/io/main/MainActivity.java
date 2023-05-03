package com.example.supermegaultraturbofacturas2remix.io.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.supermegaultraturbofacturas2remix.filtros.FiltrosActivity;
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
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        this.setSupportActionBar(toolbar);

        //Creo el menu
        MenuHost menu = this;//Accedo al menú por defecto

        //Añado un nuevo menu
        menu.addMenuProvider(new MenuProvider() {

            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                //Inflo el menu para que se vea (es como el id pero con menu) y le paso el menu
                menuInflater.inflate(R.menu.menu_main, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                //Hago un switch para acceder al item Menu (menu que he creado -> menu_main)
                switch (menuItem.getItemId()){
                    case R.id.menuFiltros://En caso de que acceda al menu con el id del menuFiltros
                        //Intent es para viajar entre actividades entre otras muchas cosas
                        //Declaro un nuevo intent y le paso la actividad en la que estoy y la actividad a la que quiero ir
                        Intent intent = new Intent(MainActivity.this, FiltrosActivity.class);
                        //Lanzo el intent para que haga lo que quiero y como hay un startActivity tengo que destruir la actividad
                        //en caso de querer cambiar de activity lo pondría al pricipio
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }



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

