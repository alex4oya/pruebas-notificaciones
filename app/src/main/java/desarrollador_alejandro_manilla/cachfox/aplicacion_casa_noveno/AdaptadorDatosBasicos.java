package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno;

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


    public class AdaptadorDatosBasicos extends BaseAdapter {
        Activity activity;
        List<DatosBasicos> lstdatos;
        LayoutInflater inflater;

        public AdaptadorDatosBasicos(Activity activity, List<DatosBasicos> lstdatos) {
            this.activity = activity;
            this.lstdatos = lstdatos;
        }



        @Override
        public int getCount() {
            return lstdatos.size();
        }

        @Override
        public Object getItem(int position) {
            return lstdatos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            inflater= (LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView=inflater.inflate(R.layout.vista_sala,null);
            TextView Dato=(TextView)itemView.findViewById(R.id.texto);
            Dato.setText(lstdatos.get(position).getUid());
            ImageView imagen=(ImageView)itemView.findViewById(R.id.Imagen);
            String buffer;
            buffer=lstdatos.get(position).getDato();
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


