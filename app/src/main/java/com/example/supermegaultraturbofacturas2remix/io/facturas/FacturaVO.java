package com.example.supermegaultraturbofacturas2remix.io.facturas;

import android.os.Parcel;
import android.os.Parcelable;

public class FacturaVO implements Parcelable {
    private String descEstado;
    private double importeOrdenacion;
    private String fecha;

    public FacturaVO(String descEstado, double importeOrdenacion, String fecha) {
        this.descEstado = descEstado;
        this.importeOrdenacion = importeOrdenacion;
        this.fecha = fecha;
    }

    protected FacturaVO(Parcel in) {
        descEstado = in.readString();
        importeOrdenacion = in.readDouble();
        fecha = in.readString();
    }

    public static final Creator<FacturaVO> CREATOR = new Creator<FacturaVO>() {
        @Override
        public FacturaVO createFromParcel(Parcel in) {
            return new FacturaVO(in);
        }

        @Override
        public FacturaVO[] newArray(int size) {
            return new FacturaVO[size];
        }
    };

    public String getDescEstado() {
        return descEstado;
    }

    public double getImporteOrdenacion() {
        return importeOrdenacion;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(descEstado);
        dest.writeDouble(importeOrdenacion);
        dest.writeString(fecha);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

