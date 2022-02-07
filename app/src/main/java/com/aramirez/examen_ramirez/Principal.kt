package com.aramirez.examen_ramirez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Principal : AppCompatActivity() {

    private lateinit var btnPersonal:FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        asignarPersonal()

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


}