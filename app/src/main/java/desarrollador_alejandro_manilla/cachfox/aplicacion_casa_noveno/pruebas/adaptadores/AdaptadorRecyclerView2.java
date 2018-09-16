package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.pruebas.adaptadores;


import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.R;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.pruebas.Data2;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.pruebas.prueba_grid;

class RecyclerViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
    public ImageView imageView;
    public TextView textView;
    public ItemClickListener itemClickListener;


    public RecyclerViewHolder2(View itemView) {
        super(itemView);
        textView=(TextView)itemView.findViewById(R.id.textView);
        imageView=(ImageView)itemView.findViewById(R.id.imagenPrueba);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);


    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;

    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }



}

public class AdaptadorRecyclerView2 extends RecyclerView.Adapter<RecyclerViewHolder2> {
    private List<Data2> listdata= new ArrayList<Data2>();

    public AdaptadorRecyclerView2(List<Data2> listdata) {
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public RecyclerViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.item2,parent,false);
        return new RecyclerViewHolder2(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder2 holder, int position) {
        holder.textView.setText(listdata.get(position).getUid());
        String imagen=listdata.get(position).getDato();
        int apagado=(R.drawable.ic_foco_apagado2);
        int prendido=(R.drawable.ic_foco_prendido2);
        if(imagen.equals("prendido"))
            holder.imageView.setImageResource(prendido);
        else
            holder.imageView.setImageResource(apagado);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }


}
