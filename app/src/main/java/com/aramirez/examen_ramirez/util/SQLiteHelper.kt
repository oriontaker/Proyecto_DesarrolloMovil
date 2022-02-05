package com.aramirez.examen_ramirez.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context):SQLiteOpenHelper(context,Constantes.DATABASE_NAME,null,Constantes.DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase) {
        val sql = "CREATE TABLE IF NOT EXISTS "+Constantes.DATABASE_TABLE+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " nombre TEXT NOT NULL, "+
                " empresa TEXT NOT NULL, "+
                " cargo  TEXT NOT NULL, "+
                " telefono INTEGER NOT NULL);"
        p0.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.execSQL("DROP TABLE IF EXISTS"+ Constantes.DATABASE_TABLE)
        onCreate(p0)
    }

}