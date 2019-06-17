package com.example.user.aplifut.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.user.aplifut.R;
import com.example.user.aplifut.models.Equipo;

import java.util.ArrayList;


public class ListaEquiposAdapter extends RecyclerView.Adapter<ListaEquiposAdapter.ViewHolder>{

    Activity activity;
    int resource;
    ArrayList<Equipo> listaEquipo;


    public ListaEquiposAdapter(int resource,Activity activity){
        this.activity=activity;
        this.resource=resource;
        listaEquipo= new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.equipos,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Equipo equipo = listaEquipo.get(i);

        Glide.with(activity)
                .load("http://flags.fmcdn.net/data/flags/w580/" + equipo.getImg())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imgEquipo);
        viewHolder.nombreEquipo.setText(equipo.getEquipo());
        viewHolder.PJ.setText(equipo.getPJ());
        viewHolder.G.setText(equipo.getG());
        viewHolder.E.setText(equipo.getE());
        viewHolder.P.setText(equipo.getP());
        viewHolder.GF.setText(equipo.getGF());
        viewHolder.GC.setText(equipo.getGC());
        viewHolder.DG.setText(equipo.getDG());
        viewHolder.Pts.setText(equipo.getPts());
    }

    @Override
    public int getItemCount() {
        return listaEquipo.size();
    }

    public void adicionarEquipos(ArrayList<Equipo> listaEquipos) {
        this.listaEquipo=listaEquipos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgEquipo;
        TextView nombreEquipo;
        TextView PJ;
        TextView G;
        TextView E;
        TextView P;
        TextView GF;
        TextView GC;
        TextView DG;
        TextView Pts;

        public ViewHolder(View itemView) {
            super(itemView);
            imgEquipo = (ImageView) itemView.findViewById(R.id.imgEquipo);
            nombreEquipo = (TextView) itemView.findViewById(R.id.nombreEquipo);
            PJ = (TextView) itemView.findViewById(R.id.PJ);
            G = (TextView) itemView.findViewById(R.id.G);
            E = (TextView) itemView.findViewById(R.id.E);
            P = (TextView) itemView.findViewById(R.id.P);
            GF = (TextView) itemView.findViewById(R.id.GF);
            GC = (TextView) itemView.findViewById(R.id.GC);
            DG = (TextView) itemView.findViewById(R.id.DG);
            Pts = (TextView) itemView.findViewById(R.id.Pts);

        }
    }

}
