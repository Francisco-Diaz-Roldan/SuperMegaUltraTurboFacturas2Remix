package com.example.supermegaultraturbofacturas2remix.io.facturas;

//Hacemos la plantilla de lo que van a tener las facturas en una nueva clase

import java.util.List;

public class FacturasVO {

    private int numFacturas;

    private List<Factura> facturas;

    public FacturasVO(int numFacturas, List<Factura> facturas) {
        this.numFacturas = numFacturas;
        this.facturas = facturas;
    }

    public int getNumFacturas() {
        return numFacturas;
    }

    public void setNumFacturas(int numFacturas) {
        this.numFacturas = numFacturas;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public class Factura {
        private  String descEstado;
        private double importeOrdenacion;
        private  String fecha;


        /**
         * Clase para crear una Factura, que es una subclase de FacturasVO.
         * @param descEstado
         * @param importeOrdenacion
         * @param fecha
         */
        public Factura(String descEstado, double importeOrdenacion, String fecha) {
            this.descEstado = descEstado;
            this.importeOrdenacion = importeOrdenacion;
            this.fecha = fecha;
        }

        public String getDescEstado() {
            return descEstado;
        }

        public double getImporteOrdenacion() {
            return importeOrdenacion;
        }


        public String getFecha() {
            return fecha;
        }
    }
}
