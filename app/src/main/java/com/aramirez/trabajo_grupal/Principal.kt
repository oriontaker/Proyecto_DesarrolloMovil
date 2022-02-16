package com.aramirez.trabajo_grupal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Principal : AppCompatActivity() {

    private lateinit var btnPersonal:FloatingActionButton
    private lateinit var btnAplicaciones:FloatingActionButton
    private lateinit var btnSedes:FloatingActionButton
    private lateinit var btnUbiSedes:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        asignarPersonal()
        asignarAplicaciones()
        asignarSedes()
        asignarUbiSede()
    }
    private fun asignarPersonal() {
        btnPersonal =findViewById(R.id.btnPersonal)
        btnPersonal.setOnClickListener{
            abrirForm()
        }
    }
    private fun abrirForm() {
        val intent  = Intent(baseContext,InicioActivity::class.java)
        startActivity(intent)
    }

    private fun asignarAplicaciones() {
        btnAplicaciones =findViewById(R.id.btnAplicaciones)
        btnAplicaciones.setOnClickListener{
            abrirFormApp()
        }
    }
    private fun abrirFormApp() {
        val intentapp  = Intent(baseContext,inicio_app::class.java)
        startActivity(intentapp)
    }

    private fun asignarSedes() {
        btnSedes =findViewById(R.id.btnSedes)
        btnSedes.setOnClickListener{
            abrirFormSedes()
        }
    }
    private fun abrirFormSedes() {
        val intentsedes  = Intent(baseContext,inicio_sedes::class.java)
        startActivity(intentsedes)
    }

    private fun asignarUbiSede() {
        btnUbiSedes =findViewById(R.id.btnUbiSedes)
        btnUbiSedes.setOnClickListener{
            abrirUbiSede()
        }
    }

    private fun abrirUbiSede() {
        val intentUbiSede  = Intent(baseContext,maps::class.java)
        startActivity(intentUbiSede)
    }



}