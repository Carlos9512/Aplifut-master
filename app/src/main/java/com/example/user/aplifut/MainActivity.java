package com.example.user.aplifut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.user.aplifut.FutApi.FutApiService;
import com.example.user.aplifut.models.Equipo;
import com.example.user.aplifut.models.EquiposRespuesta;
import com.example.user.aplifut.models.Partido;
import com.example.user.aplifut.models.PartidosRespuesta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FUTAPI";
    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private ListaPartidosAdarpter listaPartidosAdarpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewPartidos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        listaPartidosAdarpter = new ListaPartidosAdarpter(this);

        recyclerView.setAdapter(listaPartidosAdarpter);
        
        retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/Carlos9512/JsonServer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerdatos();

    }

    private void obtenerdatos(){
        FutApiService service = retrofit.create(FutApiService.class);

        //Se hace la comunicacion para obtener todos los equipos
        Call<EquiposRespuesta> equiposRespuestaCall = service.getEquipos();
        equiposRespuestaCall.enqueue(new Callback<EquiposRespuesta>() {
            @Override
            public void onResponse(Call<EquiposRespuesta> call, Response<EquiposRespuesta> response) {

                if (response.isSuccessful()) {
                    EquiposRespuesta equiposRespuesta = response.body();

                    ArrayList<Equipo> ListaEquipos = equiposRespuesta.getEquipos();
                    for (int i=0; i < ListaEquipos.size();i++){
                        Equipo equi = ListaEquipos.get(i);
                        Log.d(TAG, "Equipo " + equi.getEquipo());
                    }

                } else{
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<EquiposRespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });

        //se hace la comunicacion para obtener 1 equipo por id
        Call<Equipo> equipoCall = service.getEquipoById(2);
        equipoCall.enqueue(new Callback<Equipo>() {
           @Override
           public void onResponse(Call<Equipo> call, Response<Equipo> response) {
               if (response.isSuccessful()) {
                   Equipo equipo = response.body();
                   Equipo equi = equipo;
                   Log.d(TAG, "Equipo Solo " + equi.getEquipo());

               } else{
                   Log.e(TAG, " onResponse: " + response.errorBody());
               }
           }
           @Override
           public void onFailure(Call<Equipo> call, Throwable t) {
               Log.e(TAG, " onFailure: " + t.getMessage());
           }
       });

        //Obtener todos los partidos
        Call<PartidosRespuesta> partidosRespuestaCall = service.getPartidos();
        partidosRespuestaCall.enqueue(new Callback<PartidosRespuesta>() {
            @Override
            public void onResponse(Call<PartidosRespuesta> call, Response<PartidosRespuesta> response) {

                if (response.isSuccessful()) {
                    PartidosRespuesta partidosRespuesta = response.body();
                    ArrayList<Partido> ListaPartidos= partidosRespuesta.getPartidos();
                    for (int i=0; i < ListaPartidos.size();i++){
                        Partido equi = ListaPartidos.get(i);
                        Log.d(TAG, "Partidos " + equi.getUrlEquipo1());
                    }
                    listaPartidosAdarpter.adicionarPartidos(ListaPartidos);
                } else{
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<PartidosRespuesta> call, Throwable t) {

            }
        });

        //se hace la comunicacion para obtener 1 partido por id
        Call<Partido> partidoCall = service.getPartidoById(2);
        partidoCall.enqueue(new Callback<Partido>() {
            @Override
            public void onResponse(Call<Partido> call, Response<Partido> response) {
                if (response.isSuccessful()) {
                    Partido partido = response.body();
                    Partido parti = partido;
                    Log.d(TAG, "Partido Solo " + parti.getNombreEquipo2());

                } else{
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<Partido> call, Throwable t) {

            }
        });
    }
}
