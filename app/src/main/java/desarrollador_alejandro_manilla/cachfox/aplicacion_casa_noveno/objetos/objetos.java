package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.objetos;

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

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.R;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.habitaciones.habitacion;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.objetos.adaptadores.AdaptadorDeObjetos;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.objetos.adaptadores.ListViewObjetos;

public class objetos extends AppCompatActivity {
    private FirebaseAuth auth;
    private String uidusuario;
    private ListView lista;
    private FirebaseDatabase mfirebasedatabase;
    private DatabaseReference mdatabasereference;
    private List<AdaptadorDeObjetos> listViewObjetos=new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetos);
        lista=(ListView)findViewById(R.id.lista);
        progressBar=(ProgressBar)findViewById(R.id.carga);


        inicioFirebase();
        escuchadorfirebase();
        escuchadorLista();

    }
    void inicioFirebase(){
        FirebaseApp.initializeApp(this);
        mfirebasedatabase=FirebaseDatabase.getInstance();
        mdatabasereference=mfirebasedatabase.getReference();
        auth= FirebaseAuth.getInstance();
        uidusuario=auth.getCurrentUser().getUid();

    }

    void escuchadorfirebase(){

        mdatabasereference.child(uidusuario).child("objetos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (listViewObjetos.size()>0)
                    listViewObjetos.clear();
                try{
                    for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                        AdaptadorDeObjetos adaptadorDeObjetos=postSnapshot.getValue(AdaptadorDeObjetos.class);
                        listViewObjetos.add(adaptadorDeObjetos);
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"error "+ e,Toast.LENGTH_LONG).show();
                }
                ListViewObjetos adaptadorDeObjetos=new ListViewObjetos(objetos.this,listViewObjetos);
                lista.setAdapter(adaptadorDeObjetos);
                progressBar.setVisibility(View.GONE);
                lista.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    void escuchadorLista(){
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdaptadorDeObjetos adaptadorDeObjetos=(AdaptadorDeObjetos)parent.getItemAtPosition(position);
                String data=adaptadorDeObjetos.getUid();
                Intent dato=new Intent(objetos.this,habitacion.class);
                dato.putExtra("llave",data);
                startActivity(dato);
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
               final Dialog dialog=new Dialog(objetos.this);
                dialog.setTitle("modificador");
                dialog.setContentView(R.layout.showbox);
                final EditText text=(EditText)dialog.findViewById(R.id.textview);
                Button modificador=(Button)dialog.findViewById(R.id.btnmodificar);
                text.setHint(listViewObjetos.get(position).getUid());
                modificador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dato= text.getText().toString();
                        mdatabasereference.child(uidusuario).child("objetos").child(listViewObjetos.get(position).getUid()).child("nombre").setValue(dato);
                        dialog.dismiss();
                    }
                });
                dialog.show();

                return true;
            }
        });
    }
}
