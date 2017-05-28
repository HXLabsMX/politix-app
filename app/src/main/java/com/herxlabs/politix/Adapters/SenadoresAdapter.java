package com.herxlabs.politix.Adapters;

/**
 * Adaptador para el activity de senadores
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.herxlabs.politix.Models.Politico;
import com.herxlabs.politix.R;
import com.herxlabs.politix.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SenadoresAdapter extends RecyclerView.Adapter<SenadoresAdapter.MyViewHolder> implements Filterable {
    private List<Politico> senListF;
    private List<Politico> senListTotal;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.persona_nombre) TextView nombre;
        @BindView(R.id.descripcion) TextView descripcion;
        @BindView(R.id.persona_foto) RoundedImageView foto;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public SenadoresAdapter(Context context, List<Politico> senadores) {
        this.senListTotal = senadores;
        this.senListF = senadores;
        this.mContext = context;
        sortByNombre();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Politico politico = senListF.get(position);
        holder.nombre.setText(politico.getNombre());
        holder.foto.setImageResource(Utils.getDrawableId(politico.getFoto()));
        holder.descripcion.setText(politico.getEstado());
    }

    @Override
    public int getItemCount() {
        return senListF.size();
    }


    public Politico getSelected(int position) {
        return senListF.get(position);
    }

    public void sortByEstado(){
        Collections.sort(this.senListTotal, new Comparator<Politico>() {
            @Override
            public int compare(Politico p1, Politico p2) {
                return p1.getEstado().compareToIgnoreCase(p2.getEstado());
            }
        });
        this.senListF = this.senListTotal;
        notifyDataSetChanged();
    }
    public void sortByNombre(){
        Collections.sort(this.senListTotal, new Comparator<Politico>() {
            @Override
            public int compare(Politico p1, Politico p2) {
                return p1.getNombre().compareToIgnoreCase(p2.getNombre());
            }
        });
        this.senListF = this.senListTotal;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    private Filter myFilter = new Filter() {

        @SuppressWarnings("unchecked")
        @Override
        public void publishResults(CharSequence constraint, FilterResults results) {
            senListF = (ArrayList<Politico>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            }
        }

        @Override
        public FilterResults performFiltering(CharSequence constraint) {
            String text;
            FilterResults filterResults = new FilterResults();
            ArrayList<Politico> temp=new ArrayList<>();

            if(constraint != null && senListTotal!=null) {
                for(Politico sen: senListTotal){
                    text = constraint.toString().toLowerCase();
                    if(sen.getNombre().toLowerCase().contains(text)){
                        temp.add(sen);
                    }else if(sen.getApellido().toLowerCase().contains(text)){
                        temp.add(sen);
                    }else if(sen.getEstado().toLowerCase().contains(text)){
                        temp.add(sen);
                    }
                }

                filterResults.values = temp;
                filterResults.count = temp.size();
            }
            return filterResults;
        }
    };

}

