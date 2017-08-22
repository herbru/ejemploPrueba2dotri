package com.example.gabriel.pruebaprog2dotri;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory fabrica, int version){
        super(contexto, nombre, fabrica, version);
    }

    @Override
    public void onCreate(SQLiteDatabase DB){
        //creo la tabla Usuarios con una columna "Usuario", y una tabla "Contrasena"
        String Creartabla = "create table Datos (Campo text)";
        DB.execSQL(Creartabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int versionAnterior, int versionNueva){

    }
}
