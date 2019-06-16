package com.example.user.aplifut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.user.aplifut.models.Partido;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class ListaPartidosAdarpter extends RecyclerView.Adapter<ListaPartidosAdarpter.ViewHolder>{

    Context context;
    ArrayList<Partido> listaPartidos;

    public ListaPartidosAdarpter(Context context){
        this.context=context;
        listaPartidos= new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.partidos,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Partido partido = listaPartidos.get(i);
        viewHolder.nombreEquipoUno.setText(partido.getNombreEquipo1());
        Glide.with(context)
                .load("http://flags.fmcdn.net/data/flags/w580/" + partido.getUrlEquipo1())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imgEquipoUno);
        viewHolder.golesEquipos.setText(partido.getGolesEquipo1()+" - " +partido.getGolesEquipo2());

        viewHolder.nombreEquipoDos.setText(partido.getNombreEquipo2());
        Glide.with(context)
                .load("http://flags.fmcdn.net/data/flags/w580/" + partido.getUrlEquipo2())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imgEquipoDos);


        viewHolder.fecha.setText(partido.getFecha());
        viewHolder.estado.setText(partido.getEstado());

        viewHolder.imgEquipoUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        Log.e(TAG, " tamaño "+ listaPartidos.size());
        return listaPartidos.size();
    }

    public void adicionarPartidos(ArrayList<Partido> listaPartidos) {

        this.listaPartidos=listaPartidos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView nombreEquipoUno;
            ImageView imgEquipoUno;

            TextView golesEquipos;

            TextView nombreEquipoDos;
            ImageView imgEquipoDos;


            TextView fecha;
            TextView estado;


            public ViewHolder(View itemView) {
                super(itemView);
                nombreEquipoUno = (TextView) itemView.findViewById(R.id.nombreEquipoUno);
                imgEquipoUno = (ImageView) itemView.findViewById(R.id.imgEquipoUno);

                golesEquipos = (TextView) itemView.findViewById(R.id.golesEquipos);

                nombreEquipoDos = (TextView) itemView.findViewById(R.id.nombreEquipoDos);
                imgEquipoDos = (ImageView) itemView.findViewById(R.id.imgEquipoDos);


                fecha = (TextView) itemView.findViewById(R.id.fecha);
                estado = (TextView) itemView.findViewById(R.id.estado);
            }
        }
}
