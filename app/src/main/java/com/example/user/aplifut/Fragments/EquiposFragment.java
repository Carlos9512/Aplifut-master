package com.example.user.aplifut.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.aplifut.FutApi.FutApiService;
import com.example.user.aplifut.Adapters.ListaEquiposAdapter;
import com.example.user.aplifut.R;
import com.example.user.aplifut.models.Equipo;
import com.example.user.aplifut.models.EquiposRespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class EquiposFragment extends Fragment {

    private static final String TAG = "FUTCopa";

    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private ListaEquiposAdapter listaEquiposAdapter;

    public EquiposFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipos, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerViewEquipos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        listaEquiposAdapter = new ListaEquiposAdapter(R.layout.equipos,getActivity());

        recyclerView.setAdapter(listaEquiposAdapter);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/Carlos9512/JsonServer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerdatos();


        return view;
    }

    private void obtenerdatos() {

        FutApiService service = retrofit.create(FutApiService.class);

        Call<EquiposRespuesta> equiposRespuestaCall = service.getEquipos();
        equiposRespuestaCall.enqueue(new Callback<EquiposRespuesta>() {
            @Override
            public void onResponse(Call<EquiposRespuesta> call, Response<EquiposRespuesta> response) {

                if (response.isSuccessful()) {
                    EquiposRespuesta equiposRespuesta = response.body();

                    ArrayList<Equipo> ListaEquipos = equiposRespuesta.getEquipos();
                    listaEquiposAdapter.adicionarEquipos(ListaEquipos);
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<EquiposRespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }

}
