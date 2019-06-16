package com.example.user.aplifut.FutApi;

import com.example.user.aplifut.models.Equipo;
import com.example.user.aplifut.models.EquiposRespuesta;
import com.example.user.aplifut.models.Partido;
import com.example.user.aplifut.models.PartidosRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FutApiService {
    @GET("db")
    Call<EquiposRespuesta> getEquipos();

    @GET("equipos/{id}")
    Call<Equipo> getEquipoById(@Path("id") int id);

    @GET("db")
    Call<PartidosRespuesta> getPartidos();

    @GET("partidos/{id}")
    Call<Partido> getPartidoById(@Path("id") int id);
 }
