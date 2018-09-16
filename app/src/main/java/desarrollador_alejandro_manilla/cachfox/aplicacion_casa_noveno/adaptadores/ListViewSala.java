package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.R;

public class ListViewSala extends BaseAdapter {
    Activity activity;
    List<sala> ListaSala;
    LayoutInflater inflater;

    public ListViewSala(Activity activity, List<sala> listaSala) {
        this.activity = activity;
        ListaSala = listaSala;
    }

    @Override
    public int getCount() {return ListaSala.size();

    }

    @Override
    public Object getItem(int position) {
        return ListaSala.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater=(LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=inflater.inflate(R.layout.vista_sala,null);
        TextView texto1=(TextView)itemView.findViewById(R.id.texto);
        texto1.setText(ListaSala.get(position).getDato());
        return itemView;
    }
}
