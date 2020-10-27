package com.btxdev.multastransito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textEmail, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textEmail = (EditText) findViewById(R.id.txtEmail);
        textPassword = (EditText) findViewById(R.id.txtPassword);

        checkCurrentUser();

        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String user = textEmail.getText().toString();
        final String password = textPassword.getText().toString();


        if(!TextUtils.isEmpty(user)&&!TextUtils.isEmpty(password)){
            savePreferencesSignIn(user, password);
            Toast.makeText(this,getString(R.string.se_ha_iniciado_sesion_como)+" \""+user+"\"", Toast.LENGTH_SHORT).show();
            moveToOtherActivity(MultasActivity.class);
        }else{
            Toast.makeText(this, R.string.err_campo_invalido,Toast.LENGTH_SHORT).show();
        }

    }

    public void checkCurrentUser() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_login),Context.MODE_PRIVATE);
        String user = sharedPreferences.getString(getString(R.string.pref_current_user), null);
        String password = sharedPreferences.getString(getString(R.string.pref_current_password), null);

        if(user!=null){
            textEmail.setText(user);
        }

        if (user!=null&&password!=null){
            Toast.makeText(this,getString(R.string.se_ha_iniciado_sesion_como)+" \""+user+"\"", Toast.LENGTH_SHORT).show();
            moveToOtherActivity(MultasActivity.class);
        }
    }

    public void moveToOtherActivity (Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        this.finish();
    }

    private void savePreferencesSignIn (String email, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_login),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.pref_current_user),email);
        editor.putString(getString(R.string.pref_current_password),password);
        editor.apply();
    }
}