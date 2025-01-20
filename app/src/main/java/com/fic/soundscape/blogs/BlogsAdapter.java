package com.fic.soundscape.blogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.soundscape.R;
import com.fic.soundscape.model.Blog;


import java.util.List;

public class BlogsAdapter extends RecyclerView.Adapter<BlogsAdapter.vh>{
    Context c;
    List<Blog> blogs;

    //Constructor
    public BlogsAdapter(Context c, List<Blog> blogs){
        this.c = c;
        this.blogs = blogs;
    }

    @NonNull
    @Override
    public BlogsAdapter.vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(c);
        View v = li.inflate(R.layout.blogs_rv_row, parent, false);
        return new BlogsAdapter.vh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogsAdapter.vh holder, int position) {
        holder.Titulo.setText(blogs.get(position).Titulo);
        holder.Descripcion.setText(blogs.get(position).Descripcion);
        holder.Fecha.setText(blogs.get(position).Fecha);

        final Blog s = blogs.get(position);
    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }

    public class vh extends RecyclerView.ViewHolder{

        TextView Titulo,Descripcion,Fecha;

        public vh(@NonNull View itemView) {
            super(itemView);
            Titulo = (TextView) itemView.findViewById(R.id.blog_row_title);
            Descripcion = (TextView) itemView.findViewById(R.id.blog_row_description);
            Fecha = (TextView) itemView.findViewById(R.id.blog_row_date);
        }
    }
}
