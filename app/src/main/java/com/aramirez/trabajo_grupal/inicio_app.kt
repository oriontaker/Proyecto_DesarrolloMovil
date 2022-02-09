package com.aramirez.trabajo_grupal

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.aramirez.trabajo_grupal.agregarupc.Aplicacion
import com.aramirez.trabajo_grupal.modelo.AplicacionDAO


class inicio_app : AppCompatActivity() {
    private lateinit var btnAgregar: FloatingActionButton
    private lateinit var rvAplicaciones:RecyclerView
    private lateinit var adaptador: AdaptadorPersonalizado
    private lateinit var aplicacionDAO:AplicacionDAO
    private lateinit var listaAplicaciones:ArrayList<Aplicacion>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_app)
        mostrarAplicaciones()
        asignarReferencia()
    }

    private fun mostrarAplicaciones() {
        aplicacionDAO = AplicacionDAO(this)
        listaAplicaciones = aplicacionDAO.getAllAplicaciones()
        Log.d("==>", listaAplicaciones.size.toString())
    }

    private fun asignarReferencia(){
        btnAgregar = findViewById(R.id.btnAgregar)
        btnAgregar.setOnClickListener {
            abrirForm()
        }

        rvAplicaciones = findViewById(R.id.rvAplicaciones)

        rvAplicaciones.layoutManager = LinearLayoutManager (this)
        adaptador = AdaptadorPersonalizado(this, listaAplicaciones)

        rvAplicaciones.adapter = adaptador

        adaptador?.setOmEliminarItem {
            eliminar(it.id)
        }
        adaptador.actualizarData()
    }

    private fun eliminar(id:Int)
    {
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje Informativo")
        ventana.setMessage("Desea eliminar la aplicación?")

        ventana.setPositiveButton("Si", { dialogInterface: DialogInterface, i:Int ->
            var mensaje = aplicacionDAO.eliminarAplicacion(id)
            mostrarAplicaciones()
            asignarReferencia()
        })
        ventana.setNegativeButton("No", null)
        ventana.create().show()

    }

    private fun abrirForm(){
        val intent = Intent(baseContext, aplicaciones::class.java)
        startActivity(intent)
    }

}