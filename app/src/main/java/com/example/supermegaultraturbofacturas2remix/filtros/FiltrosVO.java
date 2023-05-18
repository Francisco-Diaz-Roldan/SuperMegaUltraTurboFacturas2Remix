package com.example.supermegaultraturbofacturas2remix.filtros;

// Creo una clase en la que hago todos los filtros con toda la informacion que le quiero pasar
// y hago un intent para pasar los datos de una actividad a otra

import java.util.Date;
import java.util.HashMap;

public class FiltrosVO {

    //Le añadimos los componentes del activity_filtros (main)

    //Paso a tipo Date los botones para trabajar con ellos
    private String fechaDesde;
    private String fechaHasta;

    //Incluyo el double del seekbar
    private int maxImporte;

    //Hago un mapa para guardar todas las checkboxes
    private HashMap<String, Boolean> estadoCheckBox = new HashMap<>();

    //Hago el constructor con sus getters y setters

    //Constructor


    public FiltrosVO(String fechaDesde, String fechaHasta, int maxImporte, HashMap<String, Boolean> estadoCheckBox) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.maxImporte = maxImporte;
        this.estadoCheckBox = estadoCheckBox;
    }

    //Getters y setters

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public int getMaxImporte() {
        return maxImporte;
    }

    public void setMaxImporte(int maxImporte) {
        this.maxImporte = maxImporte;
    }

    public HashMap<String, Boolean> getEstadoCheckBox() {
        return estadoCheckBox;
    }

    public void setEstadoCheckBox(HashMap<String, Boolean> estadoCheckBox) {
        this.estadoCheckBox = estadoCheckBox;
    }
}
