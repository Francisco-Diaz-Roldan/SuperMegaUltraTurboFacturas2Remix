package com.example.supermegaultraturbofacturas2remix.filtros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.supermegaultraturbofacturas2remix.R;
import com.example.supermegaultraturbofacturas2remix.io.main.MainActivity;

//Creamoss la clase y hacemos que extienda de AppCompatActivity e implemente de View.OnClickListener y hacemos que implemente de aqui para luego ahorrar codigo reciclandolo
public class FiltrosActivity extends AppCompatActivity implements View.OnClickListener {

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

                        //Declaro un nuevo intent y le paso la actividad en la que estoy y la actividad a la que quiero ir peeo no hace nada
                        Intent intent = new Intent(FiltrosActivity.this, MainActivity.class);
                        //Lanzo el intent para que haga lo que quiero y como hay un startActivity tengo que destruir la actividad
                        //en caso de querer cambiar de activity lo pondría al pricipio
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });


        //Creamos los botones y les aplicamos los metodos setOnClickListener tras haber implementado implements View.OnClickListener en la clase publica

        Button btnAplicarFiltros = findViewById(R.id.aplicarFiltros);
        Button btnEliminarFiltros = findViewById(R.id.eliminarFiltros);
        btnAplicarFiltros.setOnClickListener(this);
        btnEliminarFiltros.setOnClickListener(this);
    }

    //Hacemos el metodo onClick con lo que queremos que hagan los botones al hacer Click en ellos
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.aplicarFiltros) {
            Toast.makeText(this, "Botón de aplicar", Toast.LENGTH_SHORT).show();
        }else if (view.getId() == R.id.eliminarFiltros) {
            Toast.makeText(this, "Botón de eliminar", Toast.LENGTH_SHORT).show();
        }
    }
}