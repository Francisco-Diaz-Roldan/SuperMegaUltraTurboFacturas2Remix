package com.example.supermegaultraturbofacturas2remix.filtros;

// Creo una clase en la que hago todos los filtros con toda la informacion que le quiero pasar
// y hago un intent para pasar los datos de una actividad a otra

import java.util.Date;
import java.util.HashMap;

public class FiltrosVO {

    //Le añadimos los componentes del activity_filtros (main)

    //Paso a tipo Date los botones para trabajar con ellos
    private Date fechaDesde;
    private Date fechaHasta;

    //Incluyo el double del seekbar
    private double maxImporte;

    //Hago un mapa para guardar todas las checkboxes
    private HashMap<String, Boolean> estadoCheckBox = new HashMap<>();

    //Hago el constructor con sus getters y setters

    //Constructor
    public FiltrosVO(Date fechaDesde, Date fechaHasta, double maxImporte, HashMap<String, Boolean> estadoCheckBox) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.maxImporte = maxImporte;
        this.estadoCheckBox = estadoCheckBox;
    }

    //Getters y setters
    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public double getMaxImporte() {
        return maxImporte;
    }

    public void setMaxImporte(double maxImporte) {
        this.maxImporte = maxImporte;
    }

    public HashMap<String, Boolean> getEstadoCheckBox() {
        return estadoCheckBox;
    }

    public void setEstadoCheckBox(HashMap<String, Boolean> estadoCheckBox) {
        this.estadoCheckBox = estadoCheckBox;
    }
}
