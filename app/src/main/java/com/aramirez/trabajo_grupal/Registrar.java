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

public class Registrar extends AppCompatActivity implements View.OnClickListener {
    EditText us, pas, nom,ap;
    Button reg,can;
    UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        us=(EditText)findViewById(R.id.txtUser);
        pas=(EditText)findViewById(R.id.txtPass);
        nom=(EditText)findViewById(R.id.txtNom);
        ap=(EditText)findViewById(R.id.txtApe);
        reg = (Button)findViewById(R.id.btnRegUser);
        can=(Button)findViewById(R.id.btnCancelar);
        reg.setOnClickListener(this);
        can.setOnClickListener(this);
        dao = new UsuarioDAO(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnRegUser:
                Usuario u=new Usuario();
                u.setUsuario(us.getText().toString());
                u.setPassword(pas.getText().toString());
                u.setNombre(nom.getText().toString());
                u.setApellidos(ap.getText().toString());
                if(!u.isNull()){
                    Toast.makeText(this,"ERROR:Campos vacios",Toast.LENGTH_LONG).show();
                }else if(dao.insertUsuario(u)){
                    Toast.makeText(this,"Registro Exitoso!!",Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(Registrar.this,MainLogin.class);
                    startActivity(i2);
                    finish();
                }else {
                    Toast.makeText(this,"Usuario ya registrado!!",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnCancelar:
                Intent i=new Intent(Registrar.this,MainLogin.class);
                startActivity(i);
                finish();
                break;
        }

    }
}