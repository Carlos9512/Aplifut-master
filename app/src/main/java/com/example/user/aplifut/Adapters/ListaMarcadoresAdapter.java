package com.example.user.aplifut.Adapters;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.user.aplifut.R;
import com.example.user.aplifut.Room.Marcador;
import com.example.user.aplifut.Room.MarcadorDatabaseAccesor;

import java.util.ArrayList;

public class ListaMarcadoresAdapter extends RecyclerView.Adapter<ListaMarcadoresAdapter.ViewHolder>{
    Activity activity;
    int y;
    int resource;
    ArrayList<Marcador> listaMarcadores;
    BottomNavigationView bottomNavigationView;

    public ListaMarcadoresAdapter(int resource,Activity activity){
        this.activity=activity;
        this.resource=resource;
        bottomNavigationView = (BottomNavigationView) activity.findViewById(R.id.bottomBar);
        this.y=0;
        listaMarcadores= new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.marcador,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder,int i) {

        final Marcador marcador = listaMarcadores.get(i);
        viewHolder.nombreEquipoUno.setText(marcador.getNombreEquipo1());
        Glide.with(activity)
                .load("http://flags.fmcdn.net/data/flags/w580/" + marcador.getUrlEquipo1())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imgEquipoUno);
        viewHolder.golesEquipos.setText(marcador.getGolesEquipo1()+" - " +marcador.getGolesEquipo2());

        viewHolder.nombreEquipoDos.setText(marcador.getNombreEquipo2());
        Glide.with(activity)
                .load("http://flags.fmcdn.net/data/flags/w580/" + marcador.getUrlEquipo2())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imgEquipoDos);

        viewHolder.fecha.setText(marcador.getFecha());
        viewHolder.estado.setText(marcador.getEstado());

        viewHolder.linearCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y++;
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (y==1){

                        }else if (y==2){
                            deleteMarcador(marcador);
                            Toast.makeText(activity,"Marcador Borrado",Toast.LENGTH_SHORT).show();
                            bottomNavigationView.setSelectedItemId(R.id.marcadoresItem);
                        }
                        y=0;
                    }
                },400);

            }
        });


    }

    private void deleteMarcador(final Marcador marcador) {
        class DeleteMarcador extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                MarcadorDatabaseAccesor
                        .getInstance(activity.getApplication()).marcadorDAO().deleteMarcador(marcador);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

            }
        }

        DeleteMarcador deleteMarcador= new DeleteMarcador();
        deleteMarcador.execute();
    }



    @Override
    public int getItemCount() {
        return listaMarcadores.size();
    }

    public void adicionarMarcadores(ArrayList<Marcador> listaMarcadores) {

        this.listaMarcadores=listaMarcadores;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearCard;

        TextView nombreEquipoUno;
        ImageView imgEquipoUno;

        TextView golesEquipos;

        TextView nombreEquipoDos;
        ImageView imgEquipoDos;


        TextView fecha;
        TextView estado;


        public ViewHolder(View itemView) {
            super(itemView);
            nombreEquipoUno = (TextView) itemView.findViewById(R.id.nombreEquipoUnoMarcador);
            imgEquipoUno = (ImageView) itemView.findViewById(R.id.imgEquipoUnoMarcador);

            golesEquipos = (TextView) itemView.findViewById(R.id.golesEquiposMarcador);

            nombreEquipoDos = (TextView) itemView.findViewById(R.id.nombreEquipoDosMarcador);
            imgEquipoDos = (ImageView) itemView.findViewById(R.id.imgEquipoDosMarcador);

            linearCard = (LinearLayout) itemView.findViewById(R.id.linearCardMarcador);

            fecha = (TextView) itemView.findViewById(R.id.fechaMarcador);
            estado = (TextView) itemView.findViewById(R.id.fechaMarcador);
        }
    }
}
