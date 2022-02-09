package com.aramirez.trabajo_grupal.modelo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.aramirez.trabajo_grupal.agregarupc.Aplicacion
import com.aramirez.trabajo_grupal.util.Constantes_app
import com.aramirez.trabajo_grupal.util.SQLiteHelper
import com.aramirez.trabajo_grupal.util.SQLiteHelperApp

class AplicacionDAO (context: Context) {
    private var sqLiteHelperApp: SQLiteHelperApp = SQLiteHelperApp(context)

    fun registrarAplicacion(aplicacion: Aplicacion): String {
        var respuesta =""
        val db = sqLiteHelperApp.writableDatabase
        try {

            val valores = ContentValues()
            valores.put("nombre", aplicacion.nombre)
            valores.put("url", aplicacion.url)
            valores.put("categoria", aplicacion.categoria)
            valores.put("cantidadUsuarios", aplicacion.cantidadUsuario)
            valores.put("costoPorUsuario", aplicacion.costoPorUsuario)
            valores.put("equipoCargo", aplicacion.equipoCargo)

            var resultado = db.insert(Constantes_app.DATABASE_TABLE, null, valores)

            if (resultado == -1L){
                respuesta = "Error al insertar"
            }else{
                respuesta = "Se registró correctament"
            }

        }catch (e:Exception){
            respuesta = e.message.toString()
        }finally {
            db.close()
        }

        return respuesta
    }

    fun modificarAplicacion(aplicacion: Aplicacion): String {
        var respuesta =""
        val db = sqLiteHelperApp.writableDatabase
        try {

            val valores = ContentValues()
            valores.put("nombre", aplicacion.nombre)
            valores.put("url", aplicacion.url)
            valores.put("categoria", aplicacion.categoria)
            valores.put("cantidadUsuarios", aplicacion.cantidadUsuario)
            valores.put("costoPorUsuario", aplicacion.costoPorUsuario)
            valores.put("equipoCargo", aplicacion.equipoCargo)

            var resultado = db.update(Constantes_app.DATABASE_TABLE,valores, "id=" +aplicacion.id, null)

            if (resultado == -1){
                respuesta = "Error al actualizar"
            }else{
                respuesta = "Se actualizó correctamente"
            }

        }catch (e:Exception){
            respuesta = e.message.toString()
        }finally {
            db.close()
        }

        return respuesta
    }

    fun eliminarAplicacion(id:Int): String {
        var respuesta =""
        val db = sqLiteHelperApp.writableDatabase
        try {

            var resultado = db.delete(Constantes_app.DATABASE_TABLE, "id="+id, null)

            if (resultado == -1){
                respuesta = "Error al eliminar"
            }else{
                respuesta = "Se elimino correctamente"
            }

        }catch (e:Exception){
            respuesta = e.message.toString()
        }finally {
            db.close()
        }

        return respuesta
    }

    fun  getAllAplicaciones():ArrayList<Aplicacion>{
        val listaAplicaciones:ArrayList<Aplicacion> = ArrayList()
        val query = "SELECT * FROM " + Constantes_app.DATABASE_TABLE
        val db = sqLiteHelperApp.readableDatabase
        val cursor: Cursor
        try {
            cursor = db.rawQuery(query, null)
            if (cursor.count>0){
                cursor.moveToFirst()
                do {
                    val aplicacion = Aplicacion()
                    aplicacion.id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    aplicacion.nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                    aplicacion.url =  cursor.getString(cursor.getColumnIndexOrThrow("url"))
                    aplicacion.categoria = cursor.getString(cursor.getColumnIndexOrThrow("categoria"))
                    aplicacion.cantidadUsuario  = cursor.getInt(cursor.getColumnIndexOrThrow("cantidadUsuarios"))
                    aplicacion.costoPorUsuario  = cursor.getDouble(cursor.getColumnIndexOrThrow("costoPorUsuario"))
                    aplicacion.equipoCargo = cursor.getString(cursor.getColumnIndexOrThrow("equipoCargo"))
                    listaAplicaciones.add(aplicacion)

                }while (cursor.moveToNext())
            }
        }catch (e:java.lang.Exception){
            Log.d("==>", e.message.toString())

        }finally {
            db.close()
        }

        return listaAplicaciones

    }


}