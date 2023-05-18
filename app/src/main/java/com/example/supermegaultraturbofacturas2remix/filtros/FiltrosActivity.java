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
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.supermegaultraturbofacturas2remix.R;
import com.example.supermegaultraturbofacturas2remix.constantes.Constantes;
import com.example.supermegaultraturbofacturas2remix.io.facturas.FacturaVO;
import com.example.supermegaultraturbofacturas2remix.io.main.MainActivity;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

//Creamoss la clase y hacemos que extienda de AppCompatActivity

public class FiltrosActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String fechaInicio;

    private String fechaFin;

    private FiltrosVO filtrosVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

        Button botonFechaHasta = findViewById(R.id.fechaHasta);
        Button botonFechaDesde = findViewById(R.id.fechaDesde);
        SeekBar seekBar = findViewById(R.id.seekBar);

        Button botonAplicar = findViewById(R.id.aplicarFiltros);

        //Checkbox
        //Se actualizan  las checkbox
        CheckBox chbxPagadas = findViewById(R.id.chbxPagadas);
        CheckBox chbxAnuladas = findViewById(R.id.chbxAnuladas);
        CheckBox chbxCuotaFija = findViewById(R.id.chbxCuotaFija);
        CheckBox chbxPendientesPago = findViewById(R.id.chbxPendientesPago);
        CheckBox chbxPlanPago = findViewById(R.id.chbxPlanPago);


        // Se recoge el intent con getIntent, pasarle los datos a un objeto llamado FiltrosVO y creamos un método para establecer los datos
        String datosFiltro = getIntent().getStringExtra(Constantes.FILTRO);
        if (datosFiltro != null) {
            filtrosVO = new Gson().fromJson(datosFiltro, FiltrosVO.class);
            botonFechaHasta.setText(filtrosVO.getFechaHasta());
            botonFechaDesde.setText(filtrosVO.getFechaDesde());
            seekBar.setProgress(filtrosVO.getMaxImporte());
            chbxPagadas.setChecked(filtrosVO.getEstadoCheckBox().get(Constantes.PAGADAS));             chbxPagadas.setChecked(filtrosVO.getEstadoCheckBox().get(Constantes.PAGADAS));
            chbxAnuladas.setChecked(filtrosVO.getEstadoCheckBox().get(Constantes.ANULADAS));
            chbxCuotaFija.setChecked(filtrosVO.getEstadoCheckBox().get(Constantes.CUOTA_FIJA));
            chbxPendientesPago.setChecked(filtrosVO.getEstadoCheckBox().get(Constantes.PENDIENTES_DE_PAGO));
            chbxPlanPago.setChecked(filtrosVO.getEstadoCheckBox().get(Constantes.PLAN_DE_PAGO));
        }


        //Para cargar toolbar en blanco
        cargarToolbar();

        //Funcionalidades del menu de la toolbar
        crearMenuToolBar();

        // Botones Fecha


        botonFechaHasta.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd = new DatePickerDialog(FiltrosActivity.this, (view1, year1, monthofyear, dayofmonth) ->
                    botonFechaHasta.setText(dayofmonth + "/" + (monthofyear + 1) + "/" + year1), year, month, day);
            dpd.show();
        });

        botonFechaDesde.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd = new DatePickerDialog(FiltrosActivity.this, (view1, year1, monthofyear, dayofmonth) ->
                    botonFechaDesde.setText(dayofmonth + "/" + (monthofyear + 1) + "/" + year1), year, month, day);
            dpd.show();
        });

        //Seekbar
        TextView tvValorImporte = (TextView) findViewById(R.id.tvValorImporte);

        //Calculamos el valor máximo de las facturas
        int maxImporte = getIntent().getIntExtra("maxImporte", 0);

        seekBar.setMax(maxImporte);
        seekBar.setProgress(maxImporte);

        //Usamos el maximo para los textview asociados a la seekbar y controlamos el movimiento en este metodo
        pintarMaxSeekbar(maxImporte);



        //Declaro los botones
        Button fechaDesde = findViewById(R.id.fechaDesde);
        Button fechaHasta = findViewById(R.id.fechaHasta);


        //Boton para aplicar los filtros
        botonAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                Intent intent = new Intent(FiltrosActivity.this, MainActivity.class);
                HashMap<String, Boolean> estadosCB = new HashMap<>();
                estadosCB.put(Constantes.PAGADAS, chbxPagadas.isChecked());
                estadosCB.put(Constantes.ANULADAS, chbxAnuladas.isChecked());
                estadosCB.put(Constantes.CUOTA_FIJA, chbxCuotaFija.isChecked());
                estadosCB.put(Constantes.PENDIENTES_DE_PAGO, chbxPendientesPago.isChecked());
                estadosCB.put(Constantes.PLAN_DE_PAGO, chbxPlanPago.isChecked());

                //Hago un Simple Date Format para mostrar las fechas en el formato de fecha que queremos
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy");


                //Creamos un objeto filtro con los parametros obtenidos y lo enviamos
                FiltrosVO filtroEnviado = new FiltrosVO(fechaDesde.getText().toString(), fechaHasta.getText().toString(), Integer.parseInt(tvValorImporte.getText().toString()), estadosCB);
                //Para llevar el filtro a la otra clase
                intent.putExtra(Constantes.FILTRO, gson.toJson(filtroEnviado));
                startActivity(intent);
            }
        });

        //Boton para eliminar los filtros
        Button botonEliminar = findViewById(R.id.eliminarFiltros);
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//botones de fecha por defecto
                restablecerFechas();

//SeekBar por defecto
                restablecerSeekbar(maxImporte);

//Todas las checkbox por defecto
                restablecerCheckBox();
            }
        });

    }



    //Al pasar de una actividad a otra cargos la lista aqui y la metemos en una variable


    //Cambio el color de la toolbar
    private void cargarToolbar() {
        //Toolbar en blanco
        toolbar = findViewById(R.id.toolbarFiltros);
        FiltrosActivity.this.setSupportActionBar(toolbar);
    }

    //Hago el menu de la toolbar y el cambio para pasar a la otra actividad

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

    private void pintarMaxSeekbar(int maxImporte) {
        //Seekbar y textos de la seekbar, inicializar y onClick
        SeekBar seekBar = findViewById(R.id.seekBar);
        TextView tvMaxSeekBar = (TextView) findViewById(R.id.tvMaxSeekbar);
        TextView tvValorImporte = (TextView) findViewById(R.id.tvValorImporte);

        seekBar.setMax(maxImporte);
        seekBar.setProgress(maxImporte);
        tvMaxSeekBar.setText(String.valueOf(maxImporte));
        tvValorImporte.setText(String.valueOf(maxImporte));

        //Acciones a realizar en caso de mover la barra
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvValorImporte.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //No hace nada
                Log.d("onStartTrackingTouch", "onStartTrackingTouch: ha fallado");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //No hace nada
                Log.d("onStopTrackingTouch", "onStopTrackingTouch: ha fallado");
            }
        });
    }

    private void restablecerFechas() {
// Restablecer valores de fecha
        Button fechaDesde = findViewById(R.id.fechaDesde);
        fechaDesde.setText(R.string.activity_filtros_textview_dia_mes_ano);
        Button fechaHasta = findViewById(R.id.fechaHasta);
        fechaHasta.setText(R.string.activity_filtros_textview_dia_mes_ano);
    }

    private void restablecerSeekbar(int maxImporte) {
        SeekBar seekBar = findViewById(R.id.seekBar);

//Poner la seekbar al valor maximo de las facturas como predeterminado
        seekBar.setProgress(maxImporte);
    }

    private void restablecerCheckBox() {
        CheckBox chBoxPagadas = findViewById(R.id.chbxPagadas);
        CheckBox chBoxAnuladas = findViewById(R.id.chbxAnuladas);
        CheckBox chBoxCuotaFija = findViewById(R.id.chbxCuotaFija);
        CheckBox chBoxPendientesPago = findViewById(R.id.chbxPendientesPago);
        CheckBox chBoxPlanPago = findViewById(R.id.chbxPlanPago);

//Quitar el check a las checkbox
        chBoxPagadas.setChecked(false);
        chBoxAnuladas.setChecked(false);
        chBoxCuotaFija.setChecked(false);
        chBoxPendientesPago.setChecked(false);
        chBoxPlanPago.setChecked(false);
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