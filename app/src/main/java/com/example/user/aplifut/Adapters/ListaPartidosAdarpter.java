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
import com.example.user.aplifut.models.Partido;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ListaPartidosAdarpter extends RecyclerView.Adapter<ListaPartidosAdarpter.ViewHolder>{

    Activity activity;
    int y;
    int resource;
    ArrayList<Partido> listaPartidos;
    BottomNavigationView bottomNavigationView;

    public ListaPartidosAdarpter(int resource,Activity activity){
        this.activity=activity;
        this.resource=resource;
        this.y=0;
        bottomNavigationView = (BottomNavigationView) activity.findViewById(R.id.bottomBar);
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
        final Partido partido = listaPartidos.get(i);
        viewHolder.nombreEquipoUno.setText(partido.getNombreEquipo1());
        Glide.with(activity)
                .load("http://flags.fmcdn.net/data/flags/w580/" + partido.getUrlEquipo1())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imgEquipoUno);
        viewHolder.golesEquipos.setText(partido.getGolesEquipo1()+" - " +partido.getGolesEquipo2());

        viewHolder.nombreEquipoDos.setText(partido.getNombreEquipo2());
        Glide.with(activity)
                .load("http://flags.fmcdn.net/data/flags/w580/" + partido.getUrlEquipo2())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imgEquipoDos);

        viewHolder.fecha.setText(partido.getFecha());
        viewHolder.estado.setText(partido.getEstado());

        viewHolder.linearCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y++;
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (y==1){
                        }else if (y==2){
                            Marcador marcador = new Marcador(partido.getNombreEquipo1(),partido.getUrlEquipo1(),partido.getGolesEquipo1(),partido.getNombreEquipo2(),partido.getUrlEquipo2(),partido.getGolesEquipo2(),partido.getFecha(),partido.getEstadio(),partido.getEstado());
                            saveMarcador(marcador);
                            Toast.makeText(activity,R.string.marcadorGuardado,Toast.LENGTH_SHORT).show();
                        }
                        y=0;
                    }
                },400);

            }
        });


    }


    private void saveMarcador(final Marcador marcador) {
        class SaveMarcador extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //adding to database
                MarcadorDatabaseAccesor.getInstance(activity.getApplication()).marcadorDAO().insertMarcador(marcador);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }

        SaveMarcador saveMarcador = new SaveMarcador();
        saveMarcador.execute();
    }

    @Override
    public int getItemCount() {
        return listaPartidos.size();
    }

    public void adicionarPartidos(ArrayList<Partido> listaPartidos) {

        this.listaPartidos=listaPartidos;
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
                nombreEquipoUno = (TextView) itemView.findViewById(R.id.nombreEquipoUno);
                imgEquipoUno = (ImageView) itemView.findViewById(R.id.imgEquipoUno);

                golesEquipos = (TextView) itemView.findViewById(R.id.golesEquipos);

                nombreEquipoDos = (TextView) itemView.findViewById(R.id.nombreEquipoDos);
                imgEquipoDos = (ImageView) itemView.findViewById(R.id.imgEquipoDos);

                linearCard = (LinearLayout) itemView.findViewById(R.id.linearCard);

                fecha = (TextView) itemView.findViewById(R.id.fecha);
                estado = (TextView) itemView.findViewById(R.id.estado);
            }
        }
}
