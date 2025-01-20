package com.fic.soundscape.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.soundscape.R;
import com.fic.soundscape.model.New;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.vh>{
    Context c;
    List<New> news;

    //Constructor
    public NewsAdapter(Context c, List<New> news){
        this.c = c;
        this.news = news;
    }

    @NonNull
    @Override
    public NewsAdapter.vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(c);
        View v = li.inflate(R.layout.news_rv_row, parent, false);
        return new NewsAdapter.vh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.vh holder, int position) {
        holder.Titulo.setText(news.get(position).Titulo);
        holder.Resumen.setText(news.get(position).Resumen);
        holder.Categoria.setText(news.get(position).Categoria);
        holder.Fecha.setText(news.get(position).Fecha);

        final New s = news.get(position);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class vh extends RecyclerView.ViewHolder{

        TextView Titulo,Resumen,Categoria,Fecha;

        public vh(@NonNull View itemView) {
            super(itemView);
            Titulo = (TextView) itemView.findViewById(R.id.news_row_title);
            Resumen = (TextView) itemView.findViewById(R.id.news_row_summary);
            Categoria = (TextView) itemView.findViewById(R.id.news_row_category);
            Fecha = (TextView) itemView.findViewById(R.id.news_row_date);
        }
    }
}
