package com.btxdev.multastransito;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.btxdev.multastransito.adapter.GenericAdapter;
import com.btxdev.multastransito.adapter.MultaViewHolder;
import com.btxdev.multastransito.sqlite.MultaDBController;
import com.btxdev.multastransito.sqlite.TableMulta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MultasActivity extends AppCompatActivity {

    private RecyclerView recMultas;
    private GenericAdapter<MultaViewHolder> adapterMultas;
    private List<Multa> listMultas;
    private MultaDBController multaDBController;
    private FloatingActionButton fabAdd;
    private MultaDialog addMultaDialog;
    private MultaDialog viewMultaDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multas);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recMultas = findViewById(R.id.recMultas);
        fabAdd = findViewById(R.id.fabAdd);
        listMultas = new ArrayList<>();
        multaDBController = new MultaDBController(this);
        addMultaDialog = new MultaDialog(this);
        viewMultaDialog = new MultaDialog(this);

        addMultaDialog.setActionListener(new MultaDialog.ActionListener() {
            @Override
            public void onGuardar(int index, Multa multa) {
                Multa multaWithId = multaDBController.add(multa);
                if(multaWithId!=null){
                    listMultas.add(multaWithId);
                    adapterMultas.notifyItemInserted(listMultas.size()-1);
                }
                addMultaDialog.close();
            }

            @Override
            public void onEliminar(int index, Multa multa) {

            }


            @Override
            public void onCancelar() {
                addMultaDialog.close();
            }
        });

        viewMultaDialog.setActionListener(new MultaDialog.ActionListener() {
            @Override
            public void onGuardar(int index, Multa multaEdit) {
                if(multaDBController.edit(multaEdit)){
                    Multa multa = listMultas.get(index);
                    multa.setPlaca(multaEdit.getPlaca());
                    multa.setCedulaInfractor(multaEdit.getCedulaInfractor());
                    multa.setTipoComparendo(multaEdit.getTipoComparendo());
                    multa.setModelo(multaEdit.getModelo());
                    multa.setDireccionInfraccion(multaEdit.getDireccionInfraccion());
                    adapterMultas.notifyItemChanged(index);
                }
                viewMultaDialog.close();
            }

            @Override
            public void onEliminar(int index, Multa multa) {
                if(multaDBController.remove(multa.getId())){
                    listMultas.remove(index);
                    adapterMultas.notifyItemRemoved(index);
                }
                viewMultaDialog.close();
            }

            @Override
            public void onCancelar() {
                viewMultaDialog.close();
            }
        });

        adapterMultas = new GenericAdapter<>(this, R.layout.view_holder_multa, new GenericAdapter.InstanceCallback<MultaViewHolder>() {
            @Override
            public MultaViewHolder onCreateViewHolder(View view) {
                return new MultaViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull final MultaViewHolder viewHolder, final int i) {
                final Multa multa = listMultas.get(viewHolder.getAdapterPosition());

                viewHolder.getTxtPlaca().setText(multa.getPlaca());
                viewHolder.getTxtModelo().setText(multa.getModelo());
                viewHolder.getTxtDireccionInf().setText(multa.getDireccionInfraccion());
                viewHolder.getTxtTipoComp().setText(multa.getTipoComparendo());
                viewHolder.getTxtCedulaInf().setText(multa.getCedulaInfractor());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewMultaDialog.show(viewHolder.getAdapterPosition(), multa);
                    }
                });

            }

            @Override
            public int getItemCount() {
                return listMultas.size();
            }
        });

        recMultas.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fabAdd.isShown())
                    fabAdd.hide();
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    fabAdd.show();
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMultaDialog.show();
            }
        });

        adapterMultas.setOn(recMultas, false);

        setupActionBarSpinner();
    }

    private void setupActionBarSpinner(){
        String[] tipoComparendo = getResources().getStringArray(R.array.tipo_comparendo_filtrar);
        final ArrayAdapter<String> adapterTipoComp = new ArrayAdapter<>(this, R.layout.dropdown_item_w, tipoComparendo);
        View spinnerView = getLayoutInflater().inflate(R.layout.actionbar_custom, null);
        final Spinner spinner = spinnerView.findViewById(R.id.spinner);
        adapterTipoComp.setDropDownViewResource(R.layout.dropdown_item);
        getSupportActionBar().setCustomView(spinnerView);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        spinner.setAdapter(adapterTipoComp);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = spinner.getSelectedItem().toString();
                if(selected.equals(getString(R.string.tipo_comp_todos))){
                    listMultas = multaDBController.list();
                }else if(selected.equals(getString(R.string.tipo_comp_luz_roja))){
                    listMultas = multaDBController.listWithFilter(TableMulta.col_tipo_comparendo, getString(R.string.tipo_comp_luz_roja));
                }else if(selected.equals(getString(R.string.tipo_comp_mal_parqueo))){
                    listMultas = multaDBController.listWithFilter(TableMulta.col_tipo_comparendo, getString(R.string.tipo_comp_mal_parqueo));
                }else if(selected.equals(getString(R.string.tipo_comp_papeles_vencidos))){
                    listMultas = multaDBController.listWithFilter(TableMulta.col_tipo_comparendo, getString(R.string.tipo_comp_papeles_vencidos));
                }
                adapterMultas.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_multas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuLogout){
            logUp();
        }
        return super.onOptionsItemSelected(item);
    }

    public void logUp() {
        SharedPreferences sharedPreferences = MultasActivity.this.getSharedPreferences("login_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogged", false);
        editor.apply();
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}
