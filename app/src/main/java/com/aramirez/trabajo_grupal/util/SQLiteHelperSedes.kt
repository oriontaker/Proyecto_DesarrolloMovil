package com.aramirez.trabajo_grupal.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelperSedes(context: Context):SQLiteOpenHelper(context,Constantes_sedes.DATABASE_NAME,null,Constantes_sedes.DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase) {
        val sql = "CREATE TABLE IF NOT EXISTS "+Constantes_sedes.DATABASE_TABLE+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " nombre TEXT NOT NULL, "+
                " direccion TEXT NOT NULL, "+
                " distrito  TEXT NOT NULL, "+
                " capacidad INTEGER NOT NULL);"
        p0.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.execSQL("DROP TABLE IF EXISTS"+ Constantes_sedes.DATABASE_TABLE)
        onCreate(p0)
    }

}