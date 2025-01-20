package com.fic.soundscape.chart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.soundscape.R;
import com.fic.soundscape.model.Chart;

import java.util.List;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.vh>{
    Context c;
    List<Chart> charts;

    //Constructor
    public ChartAdapter(Context c, List<Chart> charts){
        this.c = c;
        this.charts = charts;
    }

    @NonNull
    @Override
    public vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(c);
        View v = li.inflate(R.layout.chart_rv_row, parent, false);
        return new vh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull vh holder, int position) {
        holder.Puesto.setText(charts.get(position).Puesto);
        holder.Nombre_Cancion.setText(charts.get(position).Nombre_Cancion);
        holder.Nombre_Artista.setText(charts.get(position).Nombre_Artista);

        final Chart s = charts.get(position);
    }

    @Override
    public int getItemCount() {
        return charts.size();
    }

    public class vh extends RecyclerView.ViewHolder{

        TextView Puesto,Nombre_Cancion,Nombre_Artista;

        public vh(@NonNull View itemView) {
            super(itemView);
            Puesto = (TextView) itemView.findViewById(R.id.chart_row_puesto);
            Nombre_Cancion = (TextView) itemView.findViewById(R.id.chart_row_nombrecancion);
            Nombre_Artista = (TextView) itemView.findViewById(R.id.chart_row_nombreartista);
        }
    }
}
