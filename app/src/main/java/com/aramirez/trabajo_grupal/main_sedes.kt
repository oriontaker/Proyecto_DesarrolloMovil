package com.aramirez.trabajo_grupal

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.aramirez.trabajo_grupal.agregarupc.Sede
import com.aramirez.trabajo_grupal.modelo.SedesDAO


class main_sedes : AppCompatActivity() {
    private lateinit var etNombre: EditText
    private lateinit var etDireccion: EditText
    private lateinit var etDistrito: EditText
    private lateinit var etCapacidad: EditText
    private lateinit var btnRegistrar: Button

    private var modificar:Boolean = false
    private var id:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sedes)

        asignarRegistro()
        recuperarDatos()
    }

    private fun recuperarDatos(){
        if(intent.hasExtra("id")){
            modificar = true
            id = intent.getIntExtra("id",0)
            etNombre.setText(intent.getStringExtra("nombre"))
            etDireccion.setText(intent.getStringExtra("direccion"))
            etDistrito.setText(intent.getStringExtra("distrito"))
            etCapacidad.setText(intent.getIntExtra("capacidad",0).toString())

        }
    }

    private fun asignarRegistro() {
        etNombre = findViewById(R.id.etNombre)
        etDireccion = findViewById(R.id.etDireccion)
        etDistrito = findViewById(R.id.etDistrito)
        etCapacidad = findViewById(R.id.etCapacidad)

        btnRegistrar = findViewById(R.id.btnRegistro)
        btnRegistrar.setOnClickListener{
            registrar()
        }
    }

    private fun registrar() {
        val nombre = etNombre.text.toString()
        val direccion = etDireccion.text.toString()
        val distrito = etDistrito.text.toString()
        val capacidad = etCapacidad.text.toString()


        if (nombre.isEmpty()){
            Toast.makeText(this,"Ingresar el nombre", Toast.LENGTH_LONG).show()
        }else {
            val obj = Sede()
            if (modificar){
                obj.id = id
            }
            obj.nombre = nombre
            obj.direccion = direccion
            obj.distrito = distrito
            obj.capacidad = capacidad.toInt()


            var daoRegistro = SedesDAO(this)
            var mensaje = ""
            if (modificar){
                mensaje = daoRegistro.modificarSede(obj)
            }else{
                mensaje = daoRegistro.registrarSede(obj)
            }
            mostrarMensaje(mensaje)
            limpiarCajas()

        }
    }

    private fun mostrarMensaje(mensaje:String) {
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar") { _: DialogInterface, _: Int ->
            val intent = Intent(this, inicio_sedes::class.java)
            startActivity(intent)
        }
        ventana.create().show()
    }

    private fun limpiarCajas() {
        etNombre.setText("")
        etDireccion.setText("")
        etDistrito.setText("")
        etCapacidad.setText("")
        etNombre.requestFocus()
    }
}