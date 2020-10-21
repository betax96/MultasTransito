package com.btxdev.multastransito;

import android.os.Parcel;
import android.os.Parcelable;

public class Multa implements Parcelable {
    private long id;
    private String placa;
    private String modelo;
    private String direccionInfraccion;
    private String tipoComparendo;
    private String cedulaInfractor;

    public Multa(String placa, String modelo, String direccionInfraccion, String tipoComparendo, String cedulaInfractor) {
        this.placa = placa;
        this.modelo = modelo;
        this.direccionInfraccion = direccionInfraccion;
        this.tipoComparendo = tipoComparendo;
        this.cedulaInfractor = cedulaInfractor;
    }

    protected Multa(Parcel in) {
        id = in.readLong();
        placa = in.readString();
        modelo = in.readString();
        direccionInfraccion = in.readString();
        tipoComparendo = in.readString();
        cedulaInfractor = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(placa);
        dest.writeString(modelo);
        dest.writeString(direccionInfraccion);
        dest.writeString(tipoComparendo);
        dest.writeString(cedulaInfractor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Multa> CREATOR = new Creator<Multa>() {
        @Override
        public Multa createFromParcel(Parcel in) {
            return new Multa(in);
        }

        @Override
        public Multa[] newArray(int size) {
            return new Multa[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDireccionInfraccion() {
        return direccionInfraccion;
    }

    public void setDireccionInfraccion(String direccionInfraccion) {
        this.direccionInfraccion = direccionInfraccion;
    }

    public String getTipoComparendo() {
        return tipoComparendo;
    }

    public void setTipoComparendo(String tipoComparendo) {
        this.tipoComparendo = tipoComparendo;
    }

    public String getCedulaInfractor() {
        return cedulaInfractor;
    }

    public void setCedulaInfractor(String cedulaInfractor) {
        this.cedulaInfractor = cedulaInfractor;
    }
}
