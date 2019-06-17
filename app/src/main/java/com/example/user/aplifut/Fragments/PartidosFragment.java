package com.example.user.aplifut.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.aplifut.FutApi.FutApiService;
import com.example.user.aplifut.Adapters.ListaPartidosAdarpter;
import com.example.user.aplifut.IntentIntegrator;
import com.example.user.aplifut.IntentResult;
import com.example.user.aplifut.R;
import com.example.user.aplifut.models.Partido;
import com.example.user.aplifut.models.PartidosRespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartidosFragment extends Fragment {

    private static final String TAG = "FUTAPI";

    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private ListaPartidosAdarpter listaPartidosAdarpter;

    public PartidosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_partidos, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerViewPartidos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        listaPartidosAdarpter = new ListaPartidosAdarpter(R.layout.partidos,getActivity());

        recyclerView.setAdapter(listaPartidosAdarpter);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/Carlos9512/JsonServer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerdatos();

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQR();
            }
        });

        return view;
    }

    private void addQR() {
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.initiateScan();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(
                    requestCode, resultCode, intent);
            if (scanResult != null) {
                // Handle successful scan
                String contents = scanResult.getContents();
                Toast.makeText(getActivity(),"leer codigo", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getActivity(), R.string.scan_canceled, Toast.LENGTH_SHORT)
                    .show();
        }

    }

    private void obtenerdatos() {
        FutApiService service = retrofit.create(FutApiService.class);

        //Obtener todos los partidos
        Call<PartidosRespuesta> partidosRespuestaCall = service.getPartidos();
        partidosRespuestaCall.enqueue(new Callback<PartidosRespuesta>() {
            @Override
            public void onResponse(Call<PartidosRespuesta> call, Response<PartidosRespuesta> response) {

                if (response.isSuccessful()) {
                    PartidosRespuesta partidosRespuesta = response.body();
                    ArrayList<Partido> ListaPartidos = partidosRespuesta.getPartidos();
                    listaPartidosAdarpter.adicionarPartidos(ListaPartidos);
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PartidosRespuesta> call, Throwable t) {

            }
        });
    }
}
