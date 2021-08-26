package com.example.tagebuch.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tagebuch.R;
import com.example.tagebuch.model.pojo.Categoria;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.ViewHolder>{

    private List<Categoria> listacategorias;
    private Context context;
    private int position = -1;

    public CategoriasAdapter(List<Categoria> listacategorias, Context context) {
        this.listacategorias = listacategorias;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjetacategorias,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tituloCategoria.setText(listacategorias.get(position).getCtitulo());
        holder.descripcionCategoria.setText(listacategorias.get(position).getCdescripcion());
        //holder.categoriaCardView.setCardBackgroundColor(Color.parseColor(listacategorias.get(position).getColor()));
        holder.categoriaCardView.setCardBackgroundColor(Color.parseColor(listacategorias.get(position).getColor()));



        holder.categoriaCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkPosition(holder.getAdapterPosition()) == -1){
                    holder.categoriaCardView.setChecked(true);
                    setPosition(holder.getAdapterPosition());
                }
                else if (checkPosition(holder.getAdapterPosition()) == 0){
                    setPosition(-1);
                    holder.categoriaCardView.setChecked(false);
                }




            }
        });
    }

    private int checkPosition(int posicionadapter){
        if (position == -1){
            return -1;
        }
        else if (position == posicionadapter){
            return 0;
        }
        return 1;
    }

    private void setPosition(int value){
        position = value;
    }
    public int getPosition(){return this.position;}

    public Categoria getSelected(){
        if (position != -1){
            return listacategorias.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return listacategorias.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tituloCategoria, descripcionCategoria;
        private MaterialCardView categoriaCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloCategoria = itemView.findViewById(R.id.TituloCategoria);
            descripcionCategoria = itemView.findViewById(R.id.DescripcionCategoria);
            categoriaCardView = itemView.findViewById(R.id.categoriaCardView);
        }
    }
}
