package com.aramirez.trabajo_grupal

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.aramirez.trabajo_grupal.agregarupc.Personal
import com.aramirez.trabajo_grupal.modelo.RegistroDAO

class MainActivity : AppCompatActivity() {

    private lateinit var txtNombre:EditText
    private lateinit var txtEmpresa:EditText
    private lateinit var txtCargo:EditText
    private lateinit var txtTelefono:EditText
    private lateinit var btnRegistrar:Button

    private var modificar:Boolean = false
    private var id:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        asignarRegistro()
        recuperarDatos()
    }

    private fun recuperarDatos(){
        if(intent.hasExtra("id")){
            modificar = true
            id = intent.getIntExtra("id",0)
            txtNombre.setText(intent.getStringExtra("nombre"))
            txtEmpresa.setText(intent.getStringExtra("empresa"))
            txtCargo.setText(intent.getStringExtra("cargo"))
            txtTelefono.setText(intent.getIntExtra("telefono",0).toString())

        }
    }

    private fun asignarRegistro() {
        txtNombre = findViewById(R.id.txtNombre)
        txtEmpresa = findViewById(R.id.txtEmpresa)
        txtCargo = findViewById(R.id.txtCargo)
        txtTelefono = findViewById(R.id.txtTelefono)

        btnRegistrar = findViewById(R.id.btnRegistro)
        btnRegistrar.setOnClickListener{
            registrar()
        }
    }


    private fun registrar() {
        val nombre = txtNombre.text.toString()
        val empresa = txtEmpresa.text.toString()
        val cargo = txtCargo.text.toString()
        val telefono = txtTelefono.text.toString()


        if (nombre.isEmpty() || empresa.isEmpty() || cargo.isEmpty() || telefono.isEmpty()){
            Toast.makeText(this,"Ingresar todos los campos", Toast.LENGTH_LONG).show()
        }else {
            val obj = Personal()
            if (modificar){
                obj.id = id
            }
            obj.nombre = nombre
            obj.empresa = empresa
            obj.cargo = cargo
            obj.telefono = telefono.toInt()


            var daoRegistro = RegistroDAO(this)
            var mensaje = ""
            if (modificar){
                mensaje = daoRegistro.modificarPersonal(obj)
            }else{
                mensaje = daoRegistro.registrarPersonal(obj)
            }
            mostrarMensaje(mensaje)
            limpiarCajas()

        }
    }

    private fun mostrarMensaje(mensaje:String) {
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar",{dialogInterface:DialogInterface, i:Int ->
            val intent = Intent(this,InicioActivity::class.java)
            startActivity(intent)
        })
        ventana.create().show()
    }

    private fun limpiarCajas() {
        txtNombre.setText("")
        txtEmpresa.setText("")
        txtCargo.setText("")
        txtTelefono.setText("")
        txtNombre.requestFocus()
    }


}