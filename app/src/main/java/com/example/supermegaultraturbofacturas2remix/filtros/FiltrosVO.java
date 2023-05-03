package com.example.supermegaultraturbofacturas2remix.filtros;

// Creo una clase en la que hago todos los filtros con toda la informacion que le quiero pasar
// y hago un intent para pasar los datos de una actividad a otra

import java.util.Date;

public class FiltrosVO {

    //Le añadimos los componentes del activity_filtros (main)

    //Paso a tipo Date los botones para trabajar con ellos
    private Date fechaDesde;
    private Date fechaHasta;

    //Incluyo el double del seekbar
    private double importeOrdenacion;

    private boolean chbxPagadas;
    private boolean chbxAnuladas;
    private boolean chbxCuotaFija;
    private boolean chbxPlanPago;
    private boolean eliminarFiltros;

    //Creamos el constructor y los getters y setters
    public FiltrosVO(Date fechaDesde, Date fechaHasta, double importeOrdenacion, boolean chbxPagadas, boolean chbxAnuladas, boolean chbxCuotaFija, boolean chbxPlanPago, boolean eliminarFiltros) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.importeOrdenacion = importeOrdenacion;
        this.chbxPagadas = chbxPagadas;
        this.chbxAnuladas = chbxAnuladas;
        this.chbxCuotaFija = chbxCuotaFija;
        this.chbxPlanPago = chbxPlanPago;
        this.eliminarFiltros = eliminarFiltros;
    }

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

    public double getImporteOrdenacion() {
        return importeOrdenacion;
    }

    public void setImporteOrdenacion(double importeOrdenacion) {
        this.importeOrdenacion = importeOrdenacion;
    }

    public boolean isChbxPagadas() {
        return chbxPagadas;
    }

    public void setChbxPagadas(boolean chbxPagadas) {
        this.chbxPagadas = chbxPagadas;
    }

    public boolean isChbxAnuladas() {
        return chbxAnuladas;
    }

    public void setChbxAnuladas(boolean chbxAnuladas) {
        this.chbxAnuladas = chbxAnuladas;
    }

    public boolean isChbxCuotaFija() {
        return chbxCuotaFija;
    }

    public void setChbxCuotaFija(boolean chbxCuotaFija) {
        this.chbxCuotaFija = chbxCuotaFija;
    }

    public boolean isChbxPlanPago() {
        return chbxPlanPago;
    }

    public void setChbxPlanPago(boolean chbxPlanPago) {
        this.chbxPlanPago = chbxPlanPago;
    }

    public boolean isEliminarFiltros() {
        return eliminarFiltros;
    }

    public void setEliminarFiltros(boolean eliminarFiltros) {
        this.eliminarFiltros = eliminarFiltros;
    }
}
