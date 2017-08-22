package com.example.gabriel.pruebaprog2dotri;

import android.icu.text.BreakIterator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentInicio extends Fragment implements View.OnClickListener{
    Button btnCargarDato;
    Button btnEliminar;
    Button btnListar;
    Button btnExiste;
    EditText txt;

    public View onCreateView(LayoutInflater InfladorDeLayouts, ViewGroup GrupoDeLaVista, Bundle DatosRecibidos) {
        View vistaADevolver = InfladorDeLayouts.inflate(R.layout.fragment_inicio, GrupoDeLaVista, false);

        txt = (EditText) vistaADevolver.findViewById(R.id.EditText);

        btnCargarDato = (Button) vistaADevolver.findViewById(R.id.btnCargarDato);
        btnEliminar = (Button) vistaADevolver.findViewById(R.id.btnBorrar);
        btnListar = (Button) vistaADevolver.findViewById(R.id.btnListar);
        btnExiste = (Button) vistaADevolver.findViewById(R.id.btnVerificar);


        btnCargarDato.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnListar.setOnClickListener(this);
        btnExiste.setOnClickListener(this);



        return vistaADevolver;
    }

    public void onClick(View Vista){
        MainActivity actividadAnfitriona = (MainActivity) getActivity();
        if (txt.getText().toString().compareTo("") == 0){
            Toast.makeText(getActivity(), "el EditText esta vacio", Toast.LENGTH_SHORT).show();
        }
        else {
            switch (Vista.getId()) {

                case R.id.btnCargarDato:
                    actividadAnfitriona.Agregar(txt.getText().toString());
                    Toast.makeText(getActivity(), "Se agrego el dato ingresado a la Db", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnVerificar:
                    if (actividadAnfitriona.existeDato(txt.getText().toString())) {
                        Toast.makeText(getActivity(), "El dato ingresado esta en la Db", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "El dato ingresado no esta en la Db", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btnBorrar:
                    actividadAnfitriona.borrarDato(txt.getText().toString());
                    Toast.makeText(getActivity(), "Se borro el dato ingresado de la Db", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnListar:
                    Fragment frgListView = new FragmentListView();
                    actividadAnfitriona.irAFragment(frgListView, R.id.AlojadorDeFragments);
                    break;
            }
        }
    }
}
