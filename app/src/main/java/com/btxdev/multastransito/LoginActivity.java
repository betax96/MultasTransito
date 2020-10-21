package com.btxdev.multastransito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textEmail, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textEmail = (EditText) findViewById(R.id.txtEmail);
        textPassword = (EditText) findViewById(R.id.txtPassword);

        currentUser();

        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);

        new MultaDialog(this).show();
    }

    @Override
    public void onClick(View v) {
        final String email = textEmail.getText().toString();
        final String password = textPassword.getText().toString();
        // TODO: Consultar a la DB en caso de necesitarlo... xD
        savePreferencesSignIn();
        moveToOtherActivity(MultasActivity.class);
    }

    public void currentUser () {
        SharedPreferences sharedPreferences = getSharedPreferences("login_preference",Context.MODE_PRIVATE);
        boolean isLogged = sharedPreferences.getBoolean("isLogged", false);
        if (isLogged) moveToOtherActivity(MultasActivity.class);
    }

    public void moveToOtherActivity (Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        this.finish();
    }

    private void savePreferencesSignIn () {
        SharedPreferences sharedPreferences = getSharedPreferences("login_preference",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogged", true);
        editor.apply();
    }
}