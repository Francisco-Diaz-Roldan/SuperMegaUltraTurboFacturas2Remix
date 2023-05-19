package com.example.supermegaultraturbofacturas2remix.io.main;

import static com.example.supermegaultraturbofacturas2remix.constantes.Constantes.FACTURAS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.supermegaultraturbofacturas2remix.constantes.Constantes;
import com.example.supermegaultraturbofacturas2remix.filtros.FiltrosActivity;
import com.example.supermegaultraturbofacturas2remix.R;
import com.example.supermegaultraturbofacturas2remix.filtros.FiltrosVO;
import com.example.supermegaultraturbofacturas2remix.io.api.APIAdapter;
import com.example.supermegaultraturbofacturas2remix.io.facturas.FacturaAdapter;
import com.example.supermegaultraturbofacturas2remix.io.facturas.FacturaVO;
import com.example.supermegaultraturbofacturas2remix.io.facturas.FacturasResult;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FacturaAdapter adapter;

    private RecyclerView rv1;

    private ArrayList<FacturaVO> listaFacturas;
    private int maxImporte;

    private FiltrosVO filtrosVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv1 = findViewById(R.id.rv1);
        rv1.setAdapter(rv1.getAdapter());
        enqueueFacturas();

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
                //Esto e  para pasar los datos a otra actividad
                if (menuItem.getItemId() == R.id.menuFiltros) {
                    Intent intent = new Intent(MainActivity.this, FiltrosActivity.class);
                    intent.putExtra("maxImporte", maxImporte);

                    Gson gson = new Gson();
                    if (filtrosVO != null) {
                        intent.putExtra(Constantes.FILTRO, gson.toJson(filtrosVO));
                    }

                    //Lanzo el intent para que haga lo que quiero y como hay un startActivity tengo que destruir la actividad
                    //en caso de querer cambiar de activity lo pondría al pricipio
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }


    //Tras el onCreate se llama al onResume (ver ciclo de vida de la aplicacion)
    @Override
    protected void onResume() {
        super.onResume();
    }
    //Parcelable hace que la clase se pueda convertir y desconvertir en un objeto de tipo Bundle, lo que hace que podamos hacer un arraylist de ese objeto


    private void enqueueFacturas() {
        //Recojo una llamada para el servicio FacturasService
        Call<FacturasResult> facturasCall = APIAdapter.getApiService().getObjetoFacturas();
        facturasCall.enqueue(new Callback<FacturasResult>() {
            @Override
            public void onResponse(Call<FacturasResult> call, Response<FacturasResult> response) {
                if(response.isSuccessful()){

                    listaFacturas = (ArrayList<FacturaVO>) response.body().getFacturas();

                    for (int i = 0; i < listaFacturas.size(); i++) {
                        int importe =  (int) listaFacturas.get(i).getImporteOrdenacion();
                        if (importe > maxImporte)
                            maxImporte = importe;
                    }

                    maxImporte++;

                    String datosFiltro = getIntent().getStringExtra(Constantes.FILTRO);
                    if (datosFiltro != null) {
                        listaFacturas = datosFiltrados(datosFiltro);
                    }

                    adapter = new FacturaAdapter(listaFacturas);
                    rv1.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<FacturasResult> call, Throwable t) {

            }
        });
    }

    private ArrayList<FacturaVO> datosFiltrados(String datosFiltro) {
        filtrosVO = new Gson().fromJson(datosFiltro, FiltrosVO.class);
        ArrayList<FacturaVO> filtroLista;

        //Comprobamos si hay que filtrar la seekbar y hacemos los cambios
        filtroLista =  comprobarSeekBar(filtrosVO.getImporteSeleccionado());
        //Vemos si hay que filtrar las fechas y las filtramos
        if(!filtrosVO.getFechaDesde().equals("día/mes/año")  ||
                !filtrosVO.getFechaHasta().equals("día/mes/año")) {
            filtroLista = comprobarFecha(filtrosVO.getFechaDesde(), filtrosVO.getFechaHasta(), filtroLista);
        }

        //Comprobamos si hay que filtrar las checkbox
        boolean hayBooleanoTrue = false;
        for (Map.Entry<String, Boolean> entry : filtrosVO.getEstadoCheckBox().entrySet()) {
            if (Boolean.TRUE.equals(entry.getValue())) {
                hayBooleanoTrue = true;
                break;
            }
        }

        //Si entra por aquí es porque hay alguna checkbox marcada
        if (hayBooleanoTrue) {
            filtroLista = comprobarCheckBox(filtrosVO.getEstadoCheckBox(), filtroLista);
        }

        TextView tvNoDatos = findViewById(R.id.tvNoDatos);

        //En caso de que no haya facturas por el filtro, mostraremos mensaje de que no hay facturas
        if(filtroLista.isEmpty()){
            tvNoDatos.setVisibility(View.VISIBLE);
        }
        return filtroLista;
    }

    private ArrayList<FacturaVO> comprobarFecha(String fechaInicio, String fechaFin, List<FacturaVO> filtroLista) {
        //Creamos lista auxiliar para despues devolverla
        ArrayList<FacturaVO> listaAux = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyyy");
        Date fechaDesde = new Date();
        Date fechaHasta = new Date();

        //Parseamos las fechas para cambiarlas de tipo String a tipo Date
        try {
            fechaDesde = sdf.parse(fechaInicio);
            fechaHasta = sdf.parse(fechaFin);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Recorremos la lista de facturas y la que coincidan la añadimos a la lista auxiliar para luego devolverla
        for (FacturaVO facturaFecha: filtroLista) {
            Date fechaFactura;
            try {
                fechaFactura = sdf.parse(facturaFecha.getFecha());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (fechaFactura.after(fechaDesde) && fechaFactura.before(fechaHasta)) {
                listaAux.add(facturaFecha);
            }
        }
        return listaAux;
    }

    //Miramos como ha movido la barra el usuario en los filtros y devolvemos la lista filtrada por la barra
    private ArrayList<FacturaVO> comprobarSeekBar(int maxImporte) {

        ArrayList<FacturaVO> listaAux = new ArrayList<>();

        for (FacturaVO facturaSeekBar : listaFacturas) {
            if (Double.parseDouble(String.valueOf(facturaSeekBar.getImporteOrdenacion())) < maxImporte) {
                listaAux.add(facturaSeekBar);
            }
        }
        return listaAux;
    }

    //Metodo filtro de las checkBox
    private ArrayList<FacturaVO> comprobarCheckBox(Map<String, Boolean> estadoCB, List<FacturaVO> filtroLista) {
        ArrayList<FacturaVO> listaAux = new ArrayList<>();

        for (FacturaVO factura: filtroLista) {
            String descEstado = factura.getDescEstado();
            if (estadoCB.containsKey(descEstado) && Boolean.TRUE.equals(estadoCB.get(descEstado))) {
                listaAux.add(factura);
            }
        }
        return listaAux;
    }
}

