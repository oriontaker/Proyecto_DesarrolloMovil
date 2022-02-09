package com.aramirez.trabajo_grupal

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aramirez.trabajo_grupal.agregarupc.Personal
import com.aramirez.trabajo_grupal.modelo.RegistroDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InicioActivity : AppCompatActivity() {

    private lateinit var btnRegistrar:FloatingActionButton
    private lateinit var rvPersonal:RecyclerView
    private lateinit var adaptador:customAdapter
    private lateinit var daoPersonal:RegistroDAO
    private lateinit var listaPersonal:ArrayList<Personal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        mostrarPersonal()
        asignarRegistro()

    }
    private fun mostrarPersonal(){
        daoPersonal = RegistroDAO(this)
        listaPersonal = daoPersonal.getAllPersonal()
        Log.d("==>",listaPersonal.size.toString())

    }

    private fun asignarRegistro() {
        btnRegistrar =findViewById(R.id.btnRegistro)
        btnRegistrar.setOnClickListener{
            abrirForm()
        }

        rvPersonal = findViewById(R.id.rvPersonal)
        rvPersonal.layoutManager = LinearLayoutManager(this)
        adaptador = customAdapter(this,listaPersonal)
        rvPersonal.adapter = adaptador

        adaptador?.setOnEliminarItem {
            eliminar(it.id)
        }
        adaptador.actualizarDatos()
    }

    private fun eliminar(id:Int){
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje informativo")
        ventana.setMessage("Desea eliminar el registro?")
        ventana.setCancelable(true)
        ventana.setPositiveButton("SI",{ dialogInterface: DialogInterface, i:Int ->
            var mensaje = daoPersonal.eliminarPersonal(id)
            mostrarPersonal()
            asignarRegistro()
        })
        ventana.setNegativeButton("NO",null)
        ventana.create().show()
    }

    private fun abrirForm() {
        val intent  = Intent(baseContext,MainActivity::class.java)
        startActivity(intent)
    }
}