package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.objetos.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.R;

public class ListViewObjetos extends BaseAdapter {
    Activity activity;
    List<AdaptadorDeObjetos> listaobjetos;
    LayoutInflater inflater;

    public ListViewObjetos(Activity activity, List<AdaptadorDeObjetos> listaobjetos) {
        this.activity = activity;
        this.listaobjetos = listaobjetos;
    }

    @Override
    public int getCount() {
        return listaobjetos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaobjetos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater=(LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=inflater.inflate(R.layout.vista_sala,null);
        TextView texto=(TextView)itemView.findViewById(R.id.texto);
        texto.setText(listaobjetos.get(position).getNombre());
        return itemView;
    }
}
