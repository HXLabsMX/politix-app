package com.herxlabs.politix.senadores;

/**
 * Adaptador para el activity de senadores
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.herxlabs.politix.R;
import com.herxlabs.politix.model.Politico;
import com.herxlabs.politix.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SenadoresAdapter extends RecyclerView.Adapter<SenadoresAdapter.MyViewHolder> {
    private List<Politico> senList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nombre) TextView nombre;
        @BindView(R.id.descripcion) TextView descripcion;
        @BindView(R.id.foto) RoundedImageView foto;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public SenadoresAdapter(List<Politico> senadores) {
        this.senList = senadores;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Politico politico = senList.get(position);
        holder.nombre.setText(politico.getNombre());
        holder.foto.setImageResource(Utils.getDrawableId(politico.getFoto()));
        holder.descripcion.setText(politico.getEstado());
    }

    @Override
    public int getItemCount() {
        return senList.size();
    }

    public void updateList(List<Politico> list){
        senList = list;
        notifyDataSetChanged();
    }

}

