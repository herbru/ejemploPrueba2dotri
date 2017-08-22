package com.example.gabriel.pruebaprog2dotri;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    FragmentManager AdministradorDeFragments;
    FragmentTransaction TransaccionesDeFragments;

    DBHelper accesoDb;
    SQLiteDatabase Db;

    ArrayList<String> listaDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdministradorDeFragments = getSupportFragmentManager();
        Fragment frgInicio = new FragmentInicio();

        TransaccionesDeFragments = AdministradorDeFragments.beginTransaction();
        TransaccionesDeFragments.replace(R.id.AlojadorDeFragments, frgInicio);
        TransaccionesDeFragments.commit();
    }

    /* Muestra un Fragment pasado como parametro en un Holder pasado como parametro*/
    public void irAFragment(Fragment fragmentAPasar, int idDelHolder) {
        FragmentManager frgManager;
        FragmentTransaction frgTransaction;

        frgManager = getSupportFragmentManager();

        frgTransaction = frgManager.beginTransaction();
        frgTransaction.replace(idDelHolder, fragmentAPasar);
        frgTransaction.commit();
    }

    //Devuelve true si pudo abrir la DB y false si no pudo
    public boolean DbAbierta() {
        boolean responder = false;
        //declaro el helper y la base de datos
        accesoDb = new DBHelper(this, "baseTP4", null, 1);
        Db = accesoDb.getWritableDatabase();

        //verifico que exista, comporbando que no sea null
        if (Db != null) {
            responder = true;
        }
        return responder;
    }

    //devuelve true si hay un registro en la db igual al parametro pasado
    public boolean existeDato(String Dato){
        //entro al if si hay una DB
        if (DbAbierta()) {
            //ejecuto una consulta que devuelve los registros
            Cursor Registros = Db.rawQuery("select * from Datos", null);
            //si encontre registros en la db entro al if
            if (Registros.moveToFirst()){
                //leo los registros hasta que no hasta que no haya mas o hasta que encuentro uno igual al parametro ingresado
                do {
                    //si el parametro pasado es igual al registro devuelvo true
                    if (Dato.compareTo(Registros.getString(0)) == 0) {
                        return true;
                    }
                } while (Registros.moveToNext());
            }
        }
        Db.close();
        return false;
    }

    public ArrayList<String> ListarRegistros(){
        ArrayList<String> listaRegistros = new ArrayList<>();
        //entro al if si hay una DB
        if (DbAbierta()) {
            //ejecuto una consulta que devuelve los registros
            Cursor Registros = Db.rawQuery("select * from Datos", null);
            //si encontre registros en la db entro al if
            if (Registros.moveToFirst()){
                //leo todos los registros
                do {
                    //agrego el registro a la lista
                    listaRegistros.add(Registros.getString(0));

                } while (Registros.moveToNext());
            }
        }
        Db.close();
        return listaRegistros;
    }

    public void borrarDato(String Dato){
        if (DbAbierta()) {
            Db.delete("Datos", "Campo" + " = " + "'" +Dato + "'", null);
        }
        Db.close();
    }

    public void Agregar(String Dato){
        //entro al if si hay una DB
        if (DbAbierta()) {
            //Instancio un registro nuevo
            ContentValues nuevoRegistro = new ContentValues();
            //lleno el registro instanciado con el nombre pasado como parametro
            nuevoRegistro.put("Campo", Dato);
            //ejecuto la consulta insert con el registro nuevo
            Db.insert("Datos", null, nuevoRegistro);
        }
        Db.close();
    }
}
