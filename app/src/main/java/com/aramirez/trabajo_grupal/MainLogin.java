package com.aramirez.trabajo_grupal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aramirez.trabajo_grupal.agregarupc.Usuario;
import com.aramirez.trabajo_grupal.modelo.UsuarioDAO;

public class MainLogin extends AppCompatActivity implements View.OnClickListener {
    EditText user,pass;
    Button btnEntrar,btnRegistrar;
    UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        user=(EditText)findViewById(R.id.txtLoginUsuario);
        pass=(EditText)findViewById(R.id.txtPassword);
        btnEntrar = (Button)findViewById(R.id.btnLoginIngresar);
        btnRegistrar=(Button)findViewById(R.id.btnLoginReg);

        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        dao = new UsuarioDAO(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLoginIngresar:
                String u=user.getText().toString();
                String p=pass.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
                }else if(dao.login(u,p)==1){
                    Usuario ux=dao.getUsuario(u,p);
                    Toast.makeText(this,"Datos correctos",Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(MainLogin.this,Principal.class);
                    startActivity(i2);
                }
                break;

            case R.id.btnLoginReg:
                Intent i=new Intent(MainLogin.this,Registrar.class);
                startActivity(i);
                break;
        }

    }
}