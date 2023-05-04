package com.example.supermegaultraturbofacturas2remix.io.facturas;

//Hacemos la plantilla de lo que van a tener las facturas en una nueva clase

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FacturasVO implements Parcelable {

    private String descEstado;
    private Double importeOrdenacion;

    private String fecha;

    private int numFacturas;

    private ArrayList<Factura> facturas;

    public FacturasVO(int numFacturas, ArrayList<Factura> facturas) {
        this.numFacturas = numFacturas;
        this.facturas = facturas;
    }

    public int getNumFacturas() {
        return numFacturas;
    }

    public void setNumFacturas(int numFacturas) {
        this.numFacturas = numFacturas;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.descEstado);
        dest.writeDouble(this.importeOrdenacion);
        dest.writeString(this.fecha);
    }

    protected FacturasVO(Parcel in) {
        this.descEstado = in.readString();
        this.importeOrdenacion = in.readDouble();
        this.fecha = in.readString();
    }

    public static final Creator<FacturasVO> CREATOR = new Creator<FacturasVO>() {
        @Override
        public FacturasVO createFromParcel(Parcel source) {
            return new FacturasVO(source);
        }

        @Override
        public FacturasVO[] newArray(int size) {
            return new FacturasVO[size];
        }
    };

    public class Factura {
        private  String descEstado;
        private double importeOrdenacion;
        private  String fecha;

        //Parcelable hace que la clase se pueda convertir y desconvertir en un objeto de tipo Bundle, lo que hace que podamos hacer un arraylist de ese objeto


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
