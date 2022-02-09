package com.aramirez.trabajo_grupal

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aramirez.trabajo_grupal.agregarupc.Sede
import com.aramirez.trabajo_grupal.modelo.SedesDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton


class inicio_sedes : AppCompatActivity() {
    private lateinit var btnNuevaSede: FloatingActionButton
    private lateinit var rvSedes: RecyclerView
    private lateinit var adapter:SedesAdapter
    private lateinit var daoSedes: SedesDAO
    private lateinit var listaSedes:ArrayList<Sede>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sedes)
        mostrarSedes()
        asignarReferencias()
    }

    private fun mostrarSedes(){
        daoSedes = SedesDAO(this)
        listaSedes = daoSedes.obtenerSedes()
        Log.d("==>",listaSedes.size.toString())

    }

    private fun asignarReferencias() {
        btnNuevaSede =findViewById(R.id.btnNuevaSede)
        btnNuevaSede.setOnClickListener{
            abrirForm()
        }

        rvSedes = findViewById(R.id.rvSedes)
        rvSedes.layoutManager = LinearLayoutManager(this)
        adapter = SedesAdapter(this,listaSedes)
        rvSedes.adapter = adapter

        adapter?.setOnEliminarSede {
            eliminar(it.id)
        }
        adapter.actualizarDatos()
    }

    private fun eliminar(id:Int){
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje informativo")
        ventana.setMessage("¿Desea eliminarla sede?")
        ventana.setCancelable(true)
        ventana.setPositiveButton("SI") { _: DialogInterface, _: Int ->
            daoSedes.eliminarSede(id)
            mostrarSedes()
            asignarReferencias()
        }
        ventana.setNegativeButton("NO",null)
        ventana.create().show()
    }

    private fun abrirForm() {
        val intent  = Intent(baseContext,main_sedes::class.java)
        startActivity(intent)
    }
}