package com.example.supermegaultraturbofacturas2remix.filtros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.supermegaultraturbofacturas2remix.R;
import com.example.supermegaultraturbofacturas2remix.io.main.MainActivity;

import java.util.Calendar;

//Creamoss la clase y hacemos que extienda de AppCompatActivity
public class FiltrosActivity extends AppCompatActivity  {

    //private Toolbar toolbar;
    //Crea la actividad cuando se entra en la actividad
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

        //Cambiar nombre de la toolbar del proyecto por el nombre que yo quiera
        FiltrosActivity.this.setTitle("Facturas");


        //Cojo la toolbar creada en el xml y la meto en el codigo
        Toolbar toolbar = findViewById(R.id.toolbarFiltros);

        this.setSupportActionBar(toolbar);

        //Creo el menu
        MenuHost menu = this;//Accedo al menú por defecto

        //Añado un nuevo menu
        menu.addMenuProvider(new MenuProvider() {

            //Creo el menu, que seria la toolbar
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                //Inflo el menu para que se vea (es como el id pero con menu) y le paso el menu
                menuInflater.inflate(R.menu.menu_main, menu);
            }

            //Cuando selecciono el elemento del menu en este caso
            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                //Hago un switch para acceder al item Menu (menu que he creado -> menu_main)
                switch (menuItem.getItemId()){
                    case R.id.menuFiltros://En caso de que acceda al menu con el id del menuFiltros
                        //Intent es para viajar entre actividades entre otras muchas cosas

                        finish();
                        return true;
                }
                return false;
            }
        });


        //Creamos los botones y les aplicamos los metodos setOnClickListener tras haber implementado implements View.OnClickListener en la clase publica

        Button btnAplicarFiltros = findViewById(R.id.aplicarFiltros);
        Button btnEliminarFiltros = findViewById(R.id.eliminarFiltros);

        //Hacemos que cada boton tenga su propio metodo onClck
        btnAplicarFiltros.setOnClickListener(new View.OnClickListener() {
            //Hacemos el metodo onClick con lo que queremos que hagan los botones al hacer Click en ellos
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
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
                        fechaDesde.setText(dayofmonth + "/" + (monthofyear+1) + "/" + year1), year, month, day);
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
                        fechaHasta.setText(dayofmonth + "/" + (monthofyear+1) + "/" + year1), year, month, day);
                dpd.show();
            }
        });
    }
}