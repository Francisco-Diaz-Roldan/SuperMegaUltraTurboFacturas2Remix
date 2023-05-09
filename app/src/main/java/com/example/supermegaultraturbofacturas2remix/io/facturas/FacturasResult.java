package com.example.supermegaultraturbofacturas2remix.io.facturas;

//Hacemos la plantilla de lo que van a tener las facturas en una nueva clase

import java.util.ArrayList;

public class FacturasResult {

    private int numFacturas;

    private ArrayList<FacturaVO> facturas;

    public FacturasResult(int numFacturas, ArrayList<FacturaVO> facturas) {
        this.numFacturas = numFacturas;
        this.facturas = facturas;
    }

    public int getNumFacturas() {
        return numFacturas;
    }

    public void setNumFacturas(int numFacturas) {
        this.numFacturas = numFacturas;
    }

    public ArrayList<FacturaVO> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<FacturaVO> facturas) {
        this.facturas = facturas;
    }

}
