package com.aramirez.trabajo_grupal

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.aramirez.trabajo_grupal.agregarupc.Aplicacion
import com.aramirez.trabajo_grupal.modelo.AplicacionDAO

class aplicaciones : AppCompatActivity() {

    private lateinit var txtNombre:EditText
    private lateinit var txtURL:EditText
    private lateinit var txtCategoria:EditText
    private lateinit var txtCantidad:EditText
    private lateinit var txtCosto:EditText
    private lateinit var txtEquipo:EditText
    private lateinit var txtNantenimiento:EditText
    private lateinit var btnRegistrar:Button

    private var modificar:Boolean = false
    private var id:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aplicaciones)
        asignarReferencia()
        recuperarDatos()
    }

    private fun recuperarDatos(){
        if(intent.hasExtra("id")){
            modificar = true
            id = intent.getIntExtra("id", 0)
            txtCantidad.setText(intent.getIntExtra("cantidadUsuario",0).toString())
            txtCategoria.setText(intent.getStringExtra("categoria"))
            txtCosto.setText(intent.getDoubleExtra("costoPorUsuario",0.0).toString())
            txtNombre.setText(intent.getStringExtra("nombre"))
            txtURL.setText(intent.getStringExtra("url"))
            txtEquipo.setText(intent.getStringExtra("equipoCargo"))

        }
    }

    private fun asignarReferencia(){
        txtNombre = findViewById(R.id.txtNombre)
        txtURL = findViewById(R.id.txtUrl)
        txtCantidad = findViewById(R.id.txtCantidad)
        txtCosto = findViewById(R.id.txtCosto)
        txtCategoria = findViewById(R.id.txtCategoria)
        txtEquipo = findViewById(R.id.txtEquipoCargo)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener{
            registrar()
        }
    }

    private fun registrar(){
        val nombre = txtNombre.text.toString()
        val url = txtURL.text.toString()
        val cantidad = txtCantidad.text.toString()
        val costo = txtCosto.text.toString()
        val categoria = txtCategoria.text.toString()
        val equipoCargo =  txtEquipo.text.toString()


        if (nombre.isEmpty() || url.isEmpty() || cantidad.isEmpty() || costo.isEmpty() ||  categoria.isEmpty() || equipoCargo.isEmpty())
        {
            Toast.makeText(this, "Debe ingresar todos los campos", Toast.LENGTH_LONG).show()

        }else{
            val obj = Aplicacion()

            if(modificar){
                obj.id=id
                btnRegistrar.text = "Actualizar";

            }

            obj.nombre = nombre
            obj.url = url
            obj.cantidadUsuario = cantidad.toInt()
            obj.costoPorUsuario = costo.toDouble()
            obj.categoria = categoria
            obj.equipoCargo =  equipoCargo

            var mensaje= ""

            var daoAplicacion = AplicacionDAO(this)

            if (modificar){
                mensaje =  daoAplicacion.modificarAplicacion(obj)
            }else {
                mensaje = daoAplicacion.registrarAplicacion(obj)
            }
            mostrarMensaje(mensaje)
            limpiarCajas()
        }
    }

    private fun mostrarMensaje(mensaje:String){
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje Informativo")
        ventana.setMessage(mensaje)

        ventana.setPositiveButton("Aceptar", {dialogInterface:DialogInterface, i:Int ->
            val intent = Intent(this, inicio_app::class.java)
            startActivity(intent)
        })
        ventana.create().show()
    }

    private fun limpiarCajas(){
        txtCategoria.setText("")
        txtCosto.setText("")
        txtCantidad.setText("")
        txtNombre.setText("")
        txtURL.setText("")
        txtNombre.requestFocus()
    }

}