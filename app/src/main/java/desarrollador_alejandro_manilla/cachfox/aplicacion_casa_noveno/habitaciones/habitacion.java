package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.habitaciones;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.DatosBasicos;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.R;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.objetos.adaptadores.AdaptadorDeObjetos;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.objetos.adaptadores.ListViewObjetos;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.objetos.objetos;

public class habitacion extends AppCompatActivity {
    private FirebaseAuth auth;
    private String uidusuario,uidobjeto,estado;
    private ListView lista;
    private FirebaseDatabase mfirebasedatabase;
    private DatabaseReference mdatabasereference;
    private ProgressBar progressBar;
    private List<AdaptadorHabitacion> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitacion);
        lista=(ListView)findViewById(R.id.lista_focos);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        if(getIntent().hasExtra("llave")){
            uidobjeto = getIntent().getExtras().getString("llave");
        }
        if(uidobjeto.equals("")){
            }



        inicioFirebase();
        escuchadorFirebase();
        escuchadorLista();
    }

    private void escuchadorFirebase() {
        mdatabasereference.child(uidusuario).child(uidobjeto).child("focos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (list.size()>0)
                    list.clear();
                try {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        AdaptadorHabitacion adaptadorHabitacion = postSnapshot.getValue(AdaptadorHabitacion.class);
                        list.add(adaptadorHabitacion);
                    }
                    ListViewHabitacion adaptadorDeObjetos=new ListViewHabitacion(habitacion.this,list);
                    lista.setAdapter(adaptadorDeObjetos);
                    progressBar.setVisibility(View.GONE);
                    lista.setVisibility(View.VISIBLE);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"error "+e,Toast.LENGTH_LONG).show();
                }
                if(list.size()<1){
                    Toast.makeText(getApplicationContext(),"habitacion no dada de alta"+"\n"+"conactenos 775-127-68-73",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    void inicioFirebase(){
        FirebaseApp.initializeApp(this);
        mfirebasedatabase= FirebaseDatabase.getInstance();
        mdatabasereference=mfirebasedatabase.getReference();
        auth= FirebaseAuth.getInstance();
        uidusuario=auth.getCurrentUser().getUid();

    }
    void escuchadorLista(){
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdaptadorHabitacion adaptadorHabitacion=(AdaptadorHabitacion) parent.getItemAtPosition(position);
                String estadoFoco=adaptadorHabitacion.getDato();
                if(estadoFoco.equals("prendido")){
                    estado="apagado";

                }else
                    estado="prendido";
                DatosBasicos datos=new DatosBasicos(adaptadorHabitacion.getUid(),estado);
                cambiarDato(datos);
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Dialog dialog=new Dialog(habitacion.this);
                dialog.setTitle("modificador");
                dialog.setContentView(R.layout.showbox);
                TextView texto=(TextView)dialog.findViewById(R.id.txtanterior);
                texto.setText("nombre anterior: "+list.get(position).getNombre()+"       ");
                final EditText text=(EditText)dialog.findViewById(R.id.textview);
                Button modificador=(Button)dialog.findViewById(R.id.btnmodificar);
                text.setHint(list.get(position).getNombre());
                modificador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dato= text.getText().toString();
                        mdatabasereference.child(uidusuario).child(uidobjeto).child("focos").child(list.get(position).getUid()).child("nombre").setValue(dato);
                        dialog.dismiss();
                    }
                });
                dialog.show();

                return true;
            }
        });
    }
    void cambiarDato (DatosBasicos datosBasicos){
        mdatabasereference.child(uidusuario).child(uidobjeto).child("focos").child(datosBasicos.getUid()).child("dato").setValue(estado);
    }
}
