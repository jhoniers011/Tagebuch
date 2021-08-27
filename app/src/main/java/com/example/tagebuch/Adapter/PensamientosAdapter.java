package com.example.tagebuch.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tagebuch.R;
import com.example.tagebuch.model.pojo.Pensamiento;

import java.io.Serializable;
import java.util.List;

public class PensamientosAdapter extends RecyclerView.Adapter<PensamientosAdapter.ViewHolder>  {

    private List<Pensamiento> listapensamientos;
    private Context context;
    private ItemClickListener clickListener;

    public PensamientosAdapter(List<Pensamiento> listapensamientos, Context context,ItemClickListener clickListener) {
        this.listapensamientos = listapensamientos;
        this.context = context;
        this.clickListener = clickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjetapensamientos,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tituloPensamiento.setText(listapensamientos.get(position).getTitulo());
        holder.fechaPensamiento.setText(listapensamientos.get(position).getFecha());
        holder.descripcionPensamiento.setText(listapensamientos.get(position).getDescripcion());
        holder.categoriaPensamiento.setText(listapensamientos.get(position).getCategoria().getCtitulo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnItemClick(listapensamientos.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return listapensamientos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tituloPensamiento, descripcionPensamiento,fechaPensamiento,categoriaPensamiento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloPensamiento = itemView.findViewById(R.id.tituloPensamientos);
            descripcionPensamiento = itemView.findViewById(R.id.DescripcionPensamiento);
            fechaPensamiento = itemView.findViewById(R.id.FechaPensamiento);
            categoriaPensamiento = itemView.findViewById(R.id.CategoriaPensamiento);

        }
    }

    public interface ItemClickListener{
        public void OnItemClick(Pensamiento pensamiento);
    }
}
