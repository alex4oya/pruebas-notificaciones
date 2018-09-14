package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.R;

public class adaptador_sala_focos extends BaseAdapter {
    Activity activity;
    List<vista_sala_focos> lista_sala_focos;
    LayoutInflater inflater;


    public adaptador_sala_focos(Activity activity, List<vista_sala_focos> lista_sala_focos) {
        this.activity = activity;
        this.lista_sala_focos = lista_sala_focos;
    }

    @Override
    public int getCount() {
        return lista_sala_focos.size();
    }

    @Override
    public Object getItem(int position) {
        return lista_sala_focos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater=(LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview=inflater.inflate(R.layout.vista_sala,null);
        TextView uid=(TextView)itemview.findViewById(R.id.texto);
        ImageView imagen=(ImageView)itemview.findViewById(R.id.imagen);
        uid.setText(lista_sala_focos.get(position).getUid());
        String buffer=lista_sala_focos.get(position).getDato();
        if(buffer.equals(1)){
            Picasso.with(activity)
                    .load(R.drawable.ic_foco_prendido2)
                    .centerCrop()
                    .into(imagen);
        }else{
            Picasso.with(activity)
                    .load(R.drawable.ic_foco_apagado2)
                    .centerCrop()
                    .into(imagen);
        }
        return itemview;
    }
}
