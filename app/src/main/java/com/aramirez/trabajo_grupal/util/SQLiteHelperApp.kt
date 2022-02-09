package com.aramirez.trabajo_grupal.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelperApp (context: Context):SQLiteOpenHelper(context, Constantes_app.DATABASE_NAME, null, Constantes_app.DATABASE_VERSION){
    override fun onCreate(p0: SQLiteDatabase) {
        val sql = "CREATE TABLE IF NOT EXISTS "+Constantes_app.DATABASE_TABLE+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " nombre TEXT NOT NULL, "+
                " url TEXT NOT NULL, "+
                " categoria TEXT NOT NULL, "+
                " cantidadUsuarios INTEGER NOT NULL, "+
                " costoPorUsuario REAL NOT NULL, "+
                " equipoCargo TEXT NOT NULL);"

        p0.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.execSQL("DROP TABLE IF EXISTS "+Constantes_app.DATABASE_NAME)
        onCreate(p0)
    }

}