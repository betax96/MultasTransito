package com.btxdev.multastransito.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.btxdev.multastransito.R;

public class MultaViewHolder extends GenericViewHolder {

    private TextView txtPlaca, txtModelo, txtDireccionInf, txtTipoComp, txtCedulaInf;


    public MultaViewHolder(@NonNull View itemView) {
        super(itemView);
        txtPlaca = itemView.findViewById(R.id.txtPlaca);
        txtModelo = itemView.findViewById(R.id.txtModelo);
        txtDireccionInf = itemView.findViewById(R.id.txtDireccionInf);
        txtTipoComp = itemView.findViewById(R.id.txtTipoComp);
        txtCedulaInf = itemView.findViewById(R.id.txtCedulaInf);
    }

    public TextView getTxtPlaca() {
        return txtPlaca;
    }

    public void setTxtPlaca(TextView txtPlaca) {
        this.txtPlaca = txtPlaca;
    }

    public TextView getTxtModelo() {
        return txtModelo;
    }

    public void setTxtModelo(TextView txtModelo) {
        this.txtModelo = txtModelo;
    }

    public TextView getTxtDireccionInf() {
        return txtDireccionInf;
    }

    public void setTxtDireccionInf(TextView txtDireccionInf) {
        this.txtDireccionInf = txtDireccionInf;
    }

    public TextView getTxtTipoComp() {
        return txtTipoComp;
    }

    public void setTxtTipoComp(TextView txtTipoComp) {
        this.txtTipoComp = txtTipoComp;
    }

    public TextView getTxtCedulaInf() {
        return txtCedulaInf;
    }

    public void setTxtCedulaInf(TextView txtCedulaInf) {
        this.txtCedulaInf = txtCedulaInf;
    }
}
