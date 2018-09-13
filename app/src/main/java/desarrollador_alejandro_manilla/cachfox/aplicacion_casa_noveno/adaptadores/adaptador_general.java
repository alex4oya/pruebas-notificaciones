package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class adaptador_general extends BaseAdapter {
    Activity activity;
    List<vista_adaptador_general> vistaGeneral;
    LayoutInflater inflater;

    @Override
    public int getCount() {
        return vistaGeneral.size();
    }

    @Override
    public Object getItem(int position) {
        return vistaGeneral.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
