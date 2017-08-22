package com.example.gabriel.pruebaprog2dotri;


import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentListView extends Fragment implements View.OnClickListener{

    Button btnListar;
    ListView lv;
    public View onCreateView(LayoutInflater InfladorDeLayouts, ViewGroup GrupoDeLaVista, Bundle DatosRecibidos) {
        View vistaADevolver = InfladorDeLayouts.inflate(R.layout.fragment_listview, GrupoDeLaVista, false);

        btnListar = (Button) vistaADevolver.findViewById(R.id.btnListar);
        lv = (ListView) vistaADevolver.findViewById(R.id.listview);

        MainActivity actividadAnfitriona = (MainActivity) getActivity();
        ArrayList<String> listaRegistros = actividadAnfitriona.ListarRegistros();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(actividadAnfitriona, R.layout.list_item, listaRegistros);

        lv.setAdapter(arrayAdapter);
        return  vistaADevolver;
    }

    public void onClick(View Vista){

    }

}
