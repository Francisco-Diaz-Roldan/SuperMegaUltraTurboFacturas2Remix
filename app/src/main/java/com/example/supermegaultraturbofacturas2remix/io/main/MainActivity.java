package com.example.supermegaultraturbofacturas2remix.io.main;

import static com.example.supermegaultraturbofacturas2remix.constantes.Constantes.FACTURAS;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Toast;

import com.example.supermegaultraturbofacturas2remix.filtros.FiltrosActivity;
import com.example.supermegaultraturbofacturas2remix.R;
import com.example.supermegaultraturbofacturas2remix.io.api.APIAdapter;
import com.example.supermegaultraturbofacturas2remix.io.facturas.FacturaAdapter;
import com.example.supermegaultraturbofacturas2remix.io.facturas.FacturasVO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FacturaAdapter adapter;

    private RecyclerView rv1;

    private ArrayList<FacturasVO.Factura> listaDeFacturas;

    private Toolbar toolbar;

    // TODO En el onCreate() hacemos la llamada al API, y en el onResume() hacemos el filtrar.
    //  Tenemos que hacer una lista fuera para acceder a los datos en toda la Actividad

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Toast.makeText(this, data.getStringExtra("hola"), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv1 = findViewById(R.id.rv1);
        rv1.setAdapter(rv1.getAdapter());


        //Cambiar nombre de la toolbar del proyecto por el nombre que yo quiera
        MainActivity.this.setTitle(FACTURAS);


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

                if (menuItem.getItemId()==R.id.menuFiltros) {
                    cambiarDeActividad();
                    return true;
                } else {
                return false;
                }
                    /*case R.id.menuFiltros://En caso de que acceda al menu con el id del menuFiltros
                        //Intent es para viajar entre actividades entre otras muchas cosas
                        //Declaro un nuevo intent y le paso la actividad en la que estoy y la actividad a la que quiero ir
                        //El intent es una clase que permite pasar de una clase a otra, aunque antes de ejecutarlo hay que añadirle cosas (los datos que le queremos pasar) al intent

                        Intent intent = new Intent(MainActivity.this, FiltrosActivity.class);
                        intent.putExtra(listaDeFacturas);

                        //Lanzo el intent para que haga lo que quiero y como hay un startActivity tengo que destruir la actividad
                        //en caso de querer cambiar de activity lo pondría al pricipio
                        startActivityForResult(intent, 1);
                        return true;
                */
            }
        });

        peticionDeFacturas();
    }



//Tras el onCreate se llama al onResume (ver ciclo de vida de la aplicacion)
   //En el onResume es donde se inicia el ciclo de la actividad
    /*@Override
    protected void onResume() {
        super.onResume();
        listaDeFacturas;
    }*/

    public void cambiarDeActividad () {
        Intent intent = new Intent(MainActivity.this , FiltrosActivity.class);
        intent.putExtra("facturas", listaDeFacturas);
        startActivity(intent);
    }

    private void peticionDeFacturas() {
        //Recojo una llamada para el servicio FacturasService
        Call<FacturasVO> facturasCall = APIAdapter.getApiService().getObjetoFacturas();
        facturasCall.enqueue(new Callback<FacturasVO>() {
            @Override
            public void onResponse(Call<FacturasVO> call, Response<FacturasVO> response) {
                if(response.isSuccessful()){
                    FacturasVO facturaObject = response.body();
                    //El log es para comprobar el tamaño de la factura por el Log
                    Log.d("onResponse facturas","Tamaño de la factura=>" + facturaObject.getFacturas().size());
                    //Obtengo la lista de facturas
                    listaDeFacturas = response.body().getFacturas();
                    adapter = new FacturaAdapter(listaDeFacturas);
                    rv1.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<FacturasVO> call, Throwable t) {
                Log.d("onFailure", "Fallo callback de enqueue");
            }
        });
    }
}

