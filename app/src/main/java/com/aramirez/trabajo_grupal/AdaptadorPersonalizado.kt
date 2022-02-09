package com.aramirez.trabajo_grupal

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aramirez.trabajo_grupal.agregarupc.Aplicacion
import org.w3c.dom.Text

class AdaptadorPersonalizado(context: Context, listaAplicacion: ArrayList<Aplicacion>):RecyclerView.Adapter<AdaptadorPersonalizado.MiVistaHolder>()
{


    private var listaAplicacion:ArrayList<Aplicacion> = listaAplicacion
    private var context = context
    private var onEliminarItem:((Aplicacion) -> Unit) ? = null

    fun setOmEliminarItem(c:(Aplicacion)->Unit){
        this.onEliminarItem = c
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ) = MiVistaHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.file,parent,false)
    )

    override fun onBindViewHolder(holder: AdaptadorPersonalizado.MiVistaHolder, position: Int) {
        var aplicacionItem = listaAplicacion[position]
        holder.setearValores(aplicacionItem)

        holder.btnEditar.setOnClickListener {
            var intent = Intent(context, aplicaciones::class.java)
            intent.putExtra("id", listaAplicacion[position].id)
            intent.putExtra("cantidadUsuarios", listaAplicacion[position].cantidadUsuario)
            intent.putExtra("categoria", listaAplicacion[position].categoria)
            intent.putExtra("costoPorUsuario", listaAplicacion[position].costoPorUsuario)
            intent.putExtra("nombre", listaAplicacion[position].nombre)
            intent.putExtra("url", listaAplicacion[position].url)
            intent.putExtra("equipoCargo", listaAplicacion[position].equipoCargo)
            context.startActivity(intent)
        }

        holder.btnEliminar.setOnClickListener {
            onEliminarItem?.invoke(aplicacionItem)
        }
    }

    override fun getItemCount(): Int {
        return listaAplicacion.size
    }

    fun actualizarData() {
        notifyDataSetChanged()
    }

    class MiVistaHolder(view: View): RecyclerView.ViewHolder(view) {
        private var filaCantidadUsuarios = view.findViewById<TextView>(R.id.filaCantidadUsuarios)
        private var filaCategoria = view.findViewById<TextView>(R.id.filaCategoria)
        private var filaCostoPorUsuario = view.findViewById<TextView>(R.id.filaCostoPorUsuario)
        private var filaNombre = view.findViewById<TextView>(R.id.filaNombre)
        private var filaURL = view.findViewById<TextView>(R.id.filaURL)
        private var filaEquipoCargo = view.findViewById<TextView>(R.id.filaEquipoCargo)





        var btnEditar = view.findViewById<ImageButton>(R.id.btnEditar)
        var btnEliminar = view.findViewById<ImageButton>(R.id.btnEliminar)






        fun setearValores(aplicacion: Aplicacion){

            filaCantidadUsuarios.text = aplicacion.cantidadUsuario.toString()
            filaCategoria.text= aplicacion.categoria
            filaCostoPorUsuario.text =aplicacion.costoPorUsuario.toString()
            filaEquipoCargo.text = aplicacion.equipoCargo
            filaNombre.text = aplicacion.nombre
            filaURL.text = aplicacion.url



        }


    }


}