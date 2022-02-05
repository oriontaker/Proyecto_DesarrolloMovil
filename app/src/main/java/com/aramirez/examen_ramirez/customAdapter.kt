package com.aramirez.examen_ramirez

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aramirez.examen_ramirez.agregarupc.Personal

class customAdapter(context: Context, listaPersonal:ArrayList<Personal>):RecyclerView.Adapter<customAdapter.MiVista>() {

    private var listaPersonal:ArrayList<Personal> = listaPersonal
    private var context = context
    private var onEliminarItem:((Personal) -> Unit)? = null


    fun setOnEliminarItem(c:(Personal) -> Unit){
        this.onEliminarItem = c
    }

    fun actualizarDatos(){
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MiVista (
        LayoutInflater.from(parent.context).inflate(R.layout.datobox,parent,false)

    )

    override fun onBindViewHolder(holder: customAdapter.MiVista, position: Int) {
        var personalItem = listaPersonal[position]
        holder.setValores(personalItem)

        holder.btnEditar.setOnClickListener {
            val intent = Intent(context,MainActivity::class.java)
            intent.putExtra("id",listaPersonal[position].id)
            intent.putExtra("nombre",listaPersonal[position].nombre)
            intent.putExtra("empresa",listaPersonal[position].empresa)
            intent.putExtra("cargo",listaPersonal[position].cargo)
            intent.putExtra("telefono",listaPersonal[position].telefono)

            context.startActivity(intent)
        }
        holder.btnEliminar.setOnClickListener {
            onEliminarItem?.invoke(personalItem)
        }
    }

    override fun getItemCount(): Int {
        return listaPersonal.size
    }

    class MiVista(view: View):RecyclerView.ViewHolder(view) {
        private var datoNombre = view.findViewById<TextView>(R.id.datoNombre)
        private var datoEmpresa = view.findViewById<TextView>(R.id.datoEmpresa)
        private var datoCargo = view.findViewById<TextView>(R.id.datoCargo)
        private var datoTelefono = view.findViewById<TextView>(R.id.datoTelefono)

        var btnEditar = view.findViewById<ImageButton>(R.id.btnEditar)
        var btnEliminar = view.findViewById<ImageButton>(R.id.btnEliminar)

        fun setValores(personal:Personal){
            datoNombre.text = personal.nombre
            datoEmpresa.text = personal.empresa
            datoCargo.text = personal.cargo
            datoTelefono.text = personal.telefono.toString()

        }

    }

}