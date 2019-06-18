package com.example.user.aplifut.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.aplifut.Adapters.ListaMarcadoresAdapter;
import com.example.user.aplifut.R;
import com.example.user.aplifut.Room.Marcador;
import com.example.user.aplifut.Room.MarcadorDatabaseAccesor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarcadoresFragment extends Fragment {

    private ArrayList<Marcador> dbMarcadores;
    private RecyclerView recyclerView;
    private ListaMarcadoresAdapter listaMarcadoresAdapter;

    public MarcadoresFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marcadores, container, false);

        dbMarcadores = new ArrayList<Marcador>();

        recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerViewMarcadores);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        listaMarcadoresAdapter = new ListaMarcadoresAdapter(R.layout.marcador,getActivity());

        recyclerView.setAdapter(listaMarcadoresAdapter);

        getMarcadores();
        return view;
    }

    private void getMarcadores() {
        class GetMarcadores extends AsyncTask<Void,Void,List<Marcador>>{

            @Override
            protected List<Marcador> doInBackground(Void... voids) {
                    List<Marcador> marcadoresList = MarcadorDatabaseAccesor
                            .getInstance(getActivity().getApplication()).marcadorDAO().loadAllMarcadores();
                    return marcadoresList;
            }

            @Override
            protected void onPostExecute(List<Marcador> marcadores) {
                super.onPostExecute(marcadores);
                dbMarcadores.clear();
                for (int i = 0; i < marcadores.size(); i++) {
                    dbMarcadores.add(marcadores.get(i));
                }
                listaMarcadoresAdapter.adicionarMarcadores(dbMarcadores);
            }
        }
        GetMarcadores getMarcadores = new GetMarcadores();
        getMarcadores.execute();
        }

}
