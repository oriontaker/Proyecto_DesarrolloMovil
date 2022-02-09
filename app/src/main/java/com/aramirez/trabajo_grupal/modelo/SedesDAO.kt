package com.aramirez.trabajo_grupal.modelo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.aramirez.trabajo_grupal.agregarupc.Sede
import com.aramirez.trabajo_grupal.util.Constantes_sedes
import com.aramirez.trabajo_grupal.util.SQLiteHelperSedes

class SedesDAO(context: Context) {
    private var sqLiteHelperSedes: SQLiteHelperSedes = SQLiteHelperSedes(context)

    fun registrarSede(sede: Sede):String{
        var rpta:String = ""
        val db = sqLiteHelperSedes.writableDatabase
        try {
            val valores = ContentValues()
            valores.put("nombre", sede.nombre)
            valores.put("direccion", sede.direccion)
            valores.put("distrito", sede.distrito)
            valores.put("capacidad", sede.capacidad)

            var resultado = db.insert(Constantes_sedes.DATABASE_TABLE,null,valores)
            if (resultado == -1L){
                rpta = "Error al registrar la sede"
            }else {
                rpta = "Sede registrada"
            }
        }catch (e:Exception){
            rpta = e.message.toString()
        }finally {
            db.close()
        }

        return rpta
    }

    fun modificarSede(sede:Sede):String{
        var rpta:String = ""
        val db = sqLiteHelperSedes.writableDatabase
        try {
            val valores = ContentValues()
            valores.put("nombre", sede.nombre)
            valores.put("direccion", sede.direccion)
            valores.put("distrito", sede.distrito)
            valores.put("capacidad", sede.capacidad)

            var resultado = db.update(Constantes_sedes.DATABASE_TABLE,valores,"id="+sede.id, null)
            if (resultado == -1){
                rpta = "Error al modificar la sede"
            }else {
                rpta = "Sede modificada"
            }
        }catch (e:Exception){
            rpta = e.message.toString()
        }finally {
            db.close()
        }


        return rpta
    }

    fun eliminarSede(id:Int):String{
        var rpta:String = ""
        val db = sqLiteHelperSedes.writableDatabase
        try {
            var resultado = db.delete(Constantes_sedes.DATABASE_TABLE,"id="+id,null)
            if (resultado == -1){
                rpta = "Error al eliminar la sede"
            }else {
                rpta = "Sede eliminada"
            }
        }catch (e:Exception){
            rpta = e.message.toString()
        }finally {
            db.close()
        }

        return rpta
    }

    fun obtenerSedes():ArrayList<Sede>{
        val listaSedes:ArrayList<Sede> = ArrayList()
        val query = "SELECT * FROM "+Constantes_sedes.DATABASE_TABLE
        val db = sqLiteHelperSedes.readableDatabase
        val cursor: Cursor

        try{
            cursor = db.rawQuery(query,null)
            if (cursor.count > 0){
                cursor.moveToFirst()
                do {
                    val sede = Sede()
                    sede.id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    sede.nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                    sede.direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"))
                    sede.distrito = cursor.getString(cursor.getColumnIndexOrThrow("distrito"))
                    sede.capacidad = cursor.getInt(cursor.getColumnIndexOrThrow("capacidad"))

                    listaSedes.add(sede)
                }while(cursor.moveToNext())

            }
        }catch (e:Exception){
            Log.d("==>",e.message.toString() )
        }finally {
            db.close()
        }
        return listaSedes
    }
}