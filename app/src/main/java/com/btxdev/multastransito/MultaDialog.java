package com.btxdev.multastransito;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialog;

import com.google.android.material.textfield.TextInputLayout;

public class MultaDialog extends AppCompatDialog {
    public MultaDialog(Context context) {
        super(context);
    }

    private TextInputLayout tilPlaca, tilModelo, tilDireccionInf, tilTipoComparendo, tilCedulaInf;
    private AutoCompleteTextView actxtTipoComp;
    private Button btnGuardar, btnCancelar, btnEliminar;
    private ActionListener actionListener;
    private Multa multa;
    private int index;

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
        actxtTipoComp = findViewById(R.id.actxtTIpoComp);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actionListener !=null){
                    actionListener.onCancelar();
                }
            }
        });

        String[] tipoComparendo = getContext().getResources().getStringArray(R.array.tipo_comparendo);
        ArrayAdapter<String> adapterTipoComp = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, tipoComparendo);
        actxtTipoComp.setAdapter(adapterTipoComp);
        actxtTipoComp.setKeyListener(null);
        actxtTipoComp.setThreshold(999);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placa = tilPlaca.getEditText().getText().toString();
                String modelo = tilModelo.getEditText().getText().toString();
                String dirreccionInf = tilDireccionInf.getEditText().getText().toString();
                String tipoComparendo = tilTipoComparendo.getEditText().getText().toString();
                String cedulaInf = tilCedulaInf.getEditText().getText().toString();

                if(!TextUtils.isEmpty(placa)&&!TextUtils.isEmpty(modelo)&&
                !TextUtils.isEmpty(dirreccionInf)&&!TextUtils.isEmpty(tipoComparendo)&&
                !TextUtils.isEmpty(cedulaInf)){

                    long id = 0;
                    if(multa!=null){
                        id = multa.getId();
                    }

                    if(actionListener!=null){
                        actionListener.onGuardar(index, new Multa(id, placa,modelo,dirreccionInf,tipoComparendo,cedulaInf));
                    }
                }else{
                    Toast.makeText(getContext(), R.string.err_campo_invalido,Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actionListener !=null){
                    actionListener.onEliminar(index, multa);
                }
            }
        });
    }

    @Override
    public void show() {
        this.multa = null;
        this.index = -1;
        super.show();
        btnEliminar.setVisibility(View.INVISIBLE);
        tilCedulaInf.getEditText().setText("");
        tilTipoComparendo.getEditText().setText("");
        tilDireccionInf.getEditText().setText("");
        tilModelo.getEditText().setText("");
        tilPlaca.getEditText().setText("");
        tilPlaca.requestFocus();
    }

    public void close(){
        this.multa = null;
        this.index = -1;
        tilCedulaInf.getEditText().setText("");
        tilTipoComparendo.getEditText().setText("");
        tilDireccionInf.getEditText().setText("");
        tilModelo.getEditText().setText("");
        tilPlaca.getEditText().setText("");
        cancel();
    }

    public void show(int index, Multa multa){
        this.multa = multa;
        this.index = index;
        super.show();
        btnEliminar.setVisibility(View.VISIBLE);
        tilCedulaInf.getEditText().setText(multa.getCedulaInfractor());
        tilPlaca.getEditText().setText(multa.getPlaca());
        tilDireccionInf.getEditText().setText(multa.getDireccionInfraccion());
        tilTipoComparendo.getEditText().setText(multa.getTipoComparendo());
        tilModelo.getEditText().setText(multa.getModelo());
        tilPlaca.requestFocus();
    }

    interface ActionListener {
        void onGuardar(int index, Multa multa);
        void onEliminar(int index, Multa multa);
        void onCancelar();
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }
}
