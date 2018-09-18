package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.habitaciones;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.R;

public class ListViewHabitacion extends BaseAdapter {
    Activity activity;
    List<AdaptadorHabitacion> listhabitacion;
    LayoutInflater inflater;

    public ListViewHabitacion(Activity activity, List<AdaptadorHabitacion> listhabitacion) {
        this.activity = activity;
        this.listhabitacion = listhabitacion;
    }

    @Override
    public int getCount() {
        return listhabitacion.size();
    }

    @Override
    public Object getItem(int position) {
        return listhabitacion.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater=(LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=inflater.inflate(R.layout.vista_sala,null);
        TextView Dato=(TextView)itemView.findViewById(R.id.texto);
        Dato.setText(listhabitacion.get(position).getNombre());
        ImageView imagen=(ImageView)itemView.findViewById(R.id.Imagen);
        String buffer;
        buffer=listhabitacion.get(position).getDato();
        if(buffer.equals("prendido")){
            Picasso.with(activity.getBaseContext())
                    .load(R.drawable.ic_foco_prendido2)
                    .fit()
                    .centerInside()
                    .into(imagen);
        }else if(buffer.equals("apagado")){
            Picasso.with(activity.getBaseContext())
                    .load(R.drawable.ic_foco_apagado2)
                    .fit()
                    .centerInside()
                    .into(imagen);
        }else
            Toast.makeText(activity,"error, en la mase de datos, contacte al administrador",Toast.LENGTH_LONG).show();
        return itemView;
    }
}
