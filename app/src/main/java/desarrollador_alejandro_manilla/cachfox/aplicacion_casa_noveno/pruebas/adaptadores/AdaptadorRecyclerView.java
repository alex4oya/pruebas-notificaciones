package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.pruebas.adaptadores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.R;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.pruebas.Data;

class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        imageView=(ImageView)itemView.findViewById(R.id.imagenPrueba);
        textView=(TextView)itemView.findViewById(R.id.textoPrueba);

    }
}

public class AdaptadorRecyclerView extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<Data> listdata=new ArrayList<Data>();

    public AdaptadorRecyclerView(List<Data> listdata) {
        this.listdata = listdata;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.item,parent,false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.imageView.setImageResource(listdata.get(position).getImagenId());
        holder.textView.setText(listdata.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }
}
