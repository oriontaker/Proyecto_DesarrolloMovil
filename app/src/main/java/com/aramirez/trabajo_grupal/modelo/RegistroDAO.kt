package com.aramirez.trabajo_grupal.modelo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.aramirez.trabajo_grupal.agregarupc.Personal
import com.aramirez.trabajo_grupal.util.Constantes
import com.aramirez.trabajo_grupal.util.SQLiteHelper

class RegistroDAO(context: Context) {
    private var sqLiteHelper:SQLiteHelper = SQLiteHelper(context)

    fun registrarPersonal(personal:Personal):String{
        var rpta:String = ""
        val db = sqLiteHelper.writableDatabase
        try {
            val valores = ContentValues()
            valores.put("nombre", personal.nombre)
            valores.put("empresa", personal.empresa)
            valores.put("cargo", personal.cargo)
            valores.put("telefono", personal.telefono)

            var resultado = db.insert(Constantes.DATABASE_TABLE,null,valores)
            if (resultado == -1L){
                rpta = "Error al ingresar registro"
            }else {
                rpta = "Registro ingresado"
            }
        }catch (e:Exception){
            rpta = e.message.toString()
        }finally {
            db.close()
        }


        return rpta
    }

    fun modificarPersonal(personal:Personal):String{
        var rpta:String = ""
        val db = sqLiteHelper.writableDatabase
        try {
            val valores = ContentValues()
            valores.put("nombre", personal.nombre)
            valores.put("empresa", personal.empresa)
            valores.put("cargo", personal.cargo)
            valores.put("telefono", personal.telefono)

            var resultado = db.update(Constantes.DATABASE_TABLE,valores,"id="+personal.id, null)
            if (resultado == -1){
                rpta = "Error al modificar registro"
            }else {
                rpta = "Registro modificado"
            }
        }catch (e:Exception){
            rpta = e.message.toString()
        }finally {
            db.close()
        }


        return rpta
    }

    fun eliminarPersonal(id:Int):String{
        var rpta:String = ""
        val db = sqLiteHelper.writableDatabase
        try {
            var resultado = db.delete(Constantes.DATABASE_TABLE,"id="+id,null)
            if (resultado == -1){
                rpta = "Error al eliminar registro"
            }else {
                rpta = "Registro eliminado"
            }
        }catch (e:Exception){
            rpta = e.message.toString()
        }finally {
            db.close()
        }


        return rpta
    }

    fun getAllPersonal():ArrayList<Personal>{
        val listaPersonal:ArrayList<Personal> = ArrayList()
        val query = "SELECT * FROM "+Constantes.DATABASE_TABLE
        val db = sqLiteHelper.readableDatabase
        val cursor:Cursor

        try{
            cursor = db.rawQuery(query,null)
            if (cursor.count > 0){
                cursor.moveToFirst()
                do {
                    val personal = Personal()
                    personal.id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    personal.nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                    personal.empresa = cursor.getString(cursor.getColumnIndexOrThrow("empresa"))
                    personal.cargo = cursor.getString(cursor.getColumnIndexOrThrow("cargo"))
                    personal.telefono = cursor.getInt(cursor.getColumnIndexOrThrow("telefono"))

                    listaPersonal.add(personal)
                }while(cursor.moveToNext())

            }
        }catch (e:Exception){
            Log.d("==>",e.message.toString() )
        }finally {
            db.close()
        }
        return listaPersonal
    }


}