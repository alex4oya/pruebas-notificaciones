package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.AdaptadorDatosBasicos;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.DatosBasicos;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.R;

public class principal extends AppCompatActivity {
    private FirebaseAuth auth;
    private String correo;
    private ListView lista;
    private FirebaseDatabase mfirebasedatabase;
    private DatabaseReference mdatabasereference;
    private List<DatosBasicos> Listadatos=new ArrayList<>();
    DatosBasicos selector;
    String estado;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        lista=(ListView)findViewById(R.id.lista);
        auth= FirebaseAuth.getInstance();
        correo=auth.getCurrentUser().getUid();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("hola");

        firebase();
        escuchadorfirebase();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DatosBasicos datosBasicos=(DatosBasicos)parent.getItemAtPosition(position);
                selector=datosBasicos;
                String estadoFoco=datosBasicos.getDato();
                if(estadoFoco.equals("prendido")){
                     estado="apagado";

                }else
                    estado="prendido";
                DatosBasicos datos=new DatosBasicos(selector.getUid(),estado);
                cambiarDato(datos);

            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Dialog dialog=new Dialog(principal.this);
                dialog.setTitle("modificador");
                dialog.setContentView(R.layout.showbox);
                final EditText text=(EditText)dialog.findViewById(R.id.textview);
                Button modificador=(Button)dialog.findViewById(R.id.btnmodificar);
                text.setHint(Listadatos.get(position).getUid());
                modificador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       String dato= text.getText().toString();
                       mdatabasereference.child(correo).child("sala").child("focos").child(Listadatos.get(position).getUid()).child("nombre").setValue(dato);
                       dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
    private void firebase() {
        FirebaseApp.initializeApp(this);
        mfirebasedatabase=FirebaseDatabase.getInstance();
        mdatabasereference=mfirebasedatabase.getReference();
    }




    private void escuchadorfirebase() {
        lista.setVisibility(View.INVISIBLE);
        mdatabasereference.child(correo).child("sala").child("focos").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                //Toast.makeText(getBaseContext(),""+t,Toast.LENGTH_LONG).show();
                if(Listadatos.size()>0)
                    Listadatos.clear();
                lista.setVisibility(View.GONE);
                try {
                    for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                       DatosBasicos datosBasicos=postSnapshot.getValue(DatosBasicos.class);
                        Listadatos.add(datosBasicos);
                    } }catch (Exception e){

                    Toast.makeText(getBaseContext(),""+e,Toast.LENGTH_LONG).show();
                }
                AdaptadorDatosBasicos adaptadorDatosBasicos=new AdaptadorDatosBasicos(principal.this,Listadatos);
                lista.setAdapter(adaptadorDatosBasicos);
                lista.setVisibility(View.VISIBLE);
                if(Listadatos.size()<1){
                    //Toast.makeText(getApplicationContext(),"No se encontro su dispositivo, revise la conexion del dispositivo conectado al circuito",
                      //      Toast.LENGTH_LONG).show();
                   // finish();
                }

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    void cambiarDato (DatosBasicos datosBasicos){
        mdatabasereference.child(correo).child("sala").child("focos").child(datosBasicos.getUid()).child("dato").setValue(estado);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.temperatura:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
