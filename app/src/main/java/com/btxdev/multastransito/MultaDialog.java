package com.btxdev.multastransito;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialog;

import com.google.android.material.textfield.TextInputLayout;

public class MultaDialog extends AppCompatDialog {
    public MultaDialog(Context context) {
        super(context);
    }

    private TextInputLayout tilPlaca, tilModelo, tilDireccionInf, tilTipoComparendo, tilCedulaInf;
    private Button btnGuardar, btnCancelar;
    private ActionListener actionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_multa);

        tilPlaca = findViewById(R.id.tilPlaca);
        tilModelo = findViewById(R.id.tilModelo);
        tilDireccionInf = findViewById(R.id.tilDirInfraccion);
        tilTipoComparendo = findViewById(R.id.tilTipoComparendo);
        tilCedulaInf = findViewById(R.id.tilCedulaInf);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actionListener !=null){
                    actionListener.onCancelar();
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void show() {
        super.show();
        tilCedulaInf.getEditText().setText("");
        tilTipoComparendo.getEditText().setText("");
        tilDireccionInf.getEditText().setText("");
        tilModelo.getEditText().setText("");
        tilPlaca.getEditText().setText("");
    }

    public void close(){
        tilCedulaInf.getEditText().setText("");
        tilTipoComparendo.getEditText().setText("");
        tilDireccionInf.getEditText().setText("");
        tilModelo.getEditText().setText("");
        tilPlaca.getEditText().setText("");
        cancel();
    }

    public void show(Multa multa){
        show();
        tilCedulaInf.getEditText().setText(multa.getCedulaInfractor());
        tilPlaca.getEditText().setText(multa.getPlaca());
        tilDireccionInf.getEditText().setText(multa.getDireccionInfraccion());
        tilTipoComparendo.getEditText().setText(multa.getTipoComparendo());
        tilModelo.getEditText().setText(multa.getModelo());
    }

    interface ActionListener {
        void onGuardar(Multa multa);
        void onCancelar();
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }
}
