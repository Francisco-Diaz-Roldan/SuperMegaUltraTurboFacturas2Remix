package com.example.supermegaultraturbofacturas2remix.filtros;

import static com.example.supermegaultraturbofacturas2remix.constantes.Constantes.BARRA_ESPACIADORA;
import static com.example.supermegaultraturbofacturas2remix.constantes.Constantes.FACTURAS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.supermegaultraturbofacturas2remix.R;
import com.example.supermegaultraturbofacturas2remix.io.facturas.FacturaVO;

import java.util.ArrayList;
import java.util.Calendar;

//Creamoss la clase y hacemos que extienda de AppCompatActivity
public class FiltrosActivity extends AppCompatActivity  {

    //Crea la actividad cuando se entra en la actividad
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

        //LISTA DE FACTURAS_________________________________________________________________________

        //Cargamos aqui la lista al pasar de una actividad a otra y me lo guarda en mi listaFactura que es una lista de FacturasVO
        ArrayList<FacturaVO> listaFactura = cargarListaDeFacturas();

        // TODO poner esta llamada al método en el sitio que sea
        int importeMaximo= calcularMaximoImporte(listaFactura);


        //TOOLBAR___________________________________________________________________________________

        //Metodo para cargar la toolbar
        crearToolBar();

        //Establecemos las funcionalidades de la toolbar
        crearMenuToolBar();






        //Creamos los botones y les aplicamos los metodos setOnClickListener tras haber implementado implements View.OnClickListener en la clase publica

        Button btnAplicarFiltros = findViewById(R.id.aplicarFiltros);
        Button btnEliminarFiltros = findViewById(R.id.eliminarFiltros);

        //Hacemos que cada boton tenga su propio metodo onClck
        btnAplicarFiltros.setOnClickListener(new View.OnClickListener() {
            //Hacemos el metodo onClick con lo que queremos que hagan los botones al hacer Click en ellos
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // TODO cambiar esto
                intent.putExtra("hola", "hola");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        btnEliminarFiltros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(this, "Botón de eliminar", Toast.LENGTH_SHORT).show();
                CheckBox chbxPagadas = (CheckBox) findViewById(R.id.chbxPagadas);
                CheckBox chbxAnuladas = (CheckBox) findViewById(R.id.chbxAnuladas);
                CheckBox chbxCuotaFija = (CheckBox) findViewById(R.id.chbxCuotaFija);
                CheckBox chbxPlanPago = (CheckBox) findViewById(R.id.chbxPlanPago);
                CheckBox chbxPendientesPago = (CheckBox) findViewById(R.id.chbxPendientesPago);
            }
        });


        Button fechaDesde = findViewById(R.id.fechaDesde);
        Button fechaHasta = findViewById(R.id.fechaHasta);
        //Para hacer que los botones de fecha funcionen


        //Hago que el boton de fechaDesde establezca la fecha
        fechaDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendario = Calendar.getInstance();
                int year = calendario.get(Calendar.YEAR);
                int month = calendario.get(Calendar.MONTH);
                int day = calendario.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(FiltrosActivity.this, (view, year1, monthofyear, dayofmonth) ->
                        fechaDesde.setText(dayofmonth + BARRA_ESPACIADORA + (monthofyear+1) + BARRA_ESPACIADORA + year1), year, month, day);
                dpd.show();
            }
        });

        //Hago que el boton de fechaHasta establezca la fecha
        fechaHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendario = Calendar.getInstance();
                int year = calendario.get(Calendar.YEAR);
                int month = calendario.get(Calendar.MONTH);
                int day = calendario.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(FiltrosActivity.this, (view, year1, monthofyear, dayofmonth) ->
                        fechaHasta.setText(dayofmonth + BARRA_ESPACIADORA + (monthofyear+1) + BARRA_ESPACIADORA + year1), year, month, day);
                dpd.show();
            }
        });
    }


    private void crearToolBar() {
        //Cambiar nombre de la toolbar del proyecto por el nombre que yo quiera
        FiltrosActivity.this.setTitle(FACTURAS);
        //Cojo la toolbar creada en el xml y la meto en el codigo
        Toolbar toolbar = findViewById(R.id.toolbarFiltros);
        this.setSupportActionBar(toolbar);
    }


    private void crearMenuToolBar() {
        //Creo el menu
        MenuHost menu = this;//Accedo al menú por defecto

        //Añado un nuevo menu
        menu.addMenuProvider(new MenuProvider() {

            //Creo el menu, que seria la toolbar
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                //Inflo el menu para que se vea (es como el id pero con menu) y le paso el menu
                menuInflater.inflate(R.menu.menu_close, menu);
            }

            //Cuando selecciono el elemento del menu en este caso
            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                //Hago un switch para acceder al item Menu (menu que he creado -> menu_main)
                if (menuItem.getItemId() == R.id.menuClose) {
                    finish();
                    return true;
                }
                return false;
            }
        });
    }




    private ArrayList<FacturaVO>  cargarListaDeFacturas() {
        ArrayList<FacturaVO> listaFactura = getIntent().getParcelableExtra("facturas");
        Log.d("tamaño facturas", ""+listaFactura.size());
        return listaFactura;
    }

    private int calcularMaximoImporte(ArrayList<FacturaVO> listaFactura) {
        int maxImporte = 0;
            for (FacturaVO factura : listaFactura) {
                double maxFactura = factura.getImporteOrdenacion();
                if (maxImporte < maxFactura) {
                    maxImporte = (int) Math.ceil(maxFactura);
                }
            }
            return maxImporte;
        }
    }
    // Restablecer valor de seekBar
        /*  SeekBar seekBar = findViewById(R.id.seekBar);
        int maxImporte = ((int) Double.parseDouble(MainActivity.maxImporte)) + 1;
        seekBar.setMax(maxImporte);
        seekBar.setProgress(maxImporte);
        TextView tvValorImporte = findViewById(R.id.tvImporte);
        tvValorImporte.setText(String.valueOf(maxImporte));
*/



