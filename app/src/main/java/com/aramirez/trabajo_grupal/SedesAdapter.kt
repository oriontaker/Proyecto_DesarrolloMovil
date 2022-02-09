package com.aramirez.trabajo_grupal

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aramirez.trabajo_grupal.agregarupc.Sede

class SedesAdapter(context: Context, listaSedes:ArrayList<Sede>): RecyclerView.Adapter<SedesAdapter.MyViewHolder>() {
    private var listaSedes:ArrayList<Sede> = listaSedes
    private var context = context
    private var onEliminarSede:((Sede) -> Unit)? = null


    fun setOnEliminarSede(c:(Sede) -> Unit){
        this.onEliminarSede = c
    }

    fun actualizarDatos(){
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_sede,parent,false)

    )

    override fun onBindViewHolder(holder: SedesAdapter.MyViewHolder, position: Int) {
        var personalItem = listaSedes[position]
        holder.setValores(personalItem)

        holder.btnEditar.setOnClickListener {
            val intent = Intent(context,main_sedes::class.java)
            intent.putExtra("id",listaSedes[position].id)
            intent.putExtra("nombre",listaSedes[position].nombre)
            intent.putExtra("direccion",listaSedes[position].direccion)
            intent.putExtra("distrito",listaSedes[position].distrito)
            intent.putExtra("capacidad",listaSedes[position].capacidad)

            context.startActivity(intent)
        }
        holder.btnEliminar.setOnClickListener {
            onEliminarSede?.invoke(personalItem)
        }
    }

    override fun getItemCount(): Int {
        return listaSedes.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var tvNombre = view.findViewById<TextView>(R.id.tvNombre)
        private var tvDireccion = view.findViewById<TextView>(R.id.tvDireccion)
        private var tvDistrito = view.findViewById<TextView>(R.id.tvDistrito)
        private var tvCapacidad = view.findViewById<TextView>(R.id.tvCapacidad)

        var btnEditar = view.findViewById<ImageButton>(R.id.btnEditar)
        var btnEliminar = view.findViewById<ImageButton>(R.id.btnEliminar)

        fun setValores(sede:Sede){
            tvNombre.text = sede.nombre
            tvDireccion.text = sede.direccion
            tvDistrito.text = sede.distrito
            tvCapacidad.text = sede.capacidad.toString()

        }

    }
}