package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.pruebas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.pruebas.adaptadores.AdaptadorRecyclerView;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.pruebas.adaptadores.AdaptadorRecyclerView2;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.pruebas.adaptadores.ItemClickListener;

public class prueba_grid extends AppCompatActivity {
    private RecyclerView recyclerView, recyclerView2;
    private AdaptadorRecyclerView adaptador;
    private AdaptadorRecyclerView2 adaptador2;
    private RecyclerView.LayoutManager layoutManager,layoutManager2;
    private List<Data> listData=new ArrayList<Data>();
    private List<Data2> listData2=new ArrayList<Data2>();
    private FirebaseDatabase mfirebasedatabase;
    private DatabaseReference mdatabasereference;
    private FirebaseAuth auth;
    private String correo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_grid);

        firebase();
        escuchadorfirebase();
        intDada();
        recyclerView=(RecyclerView)findViewById(R.id.listado1);
        recyclerView.setHasFixedSize(true);
        recyclerView2=(RecyclerView)findViewById(R.id.listado2);
        recyclerView2.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        layoutManager2=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        adaptador=new AdaptadorRecyclerView(listData);
        adaptador2=new AdaptadorRecyclerView2(listData2);
        recyclerView.setAdapter(adaptador);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //esto hace que se coloque de forma horizontal
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void firebase() {
        FirebaseApp.initializeApp(this);
        mfirebasedatabase= FirebaseDatabase.getInstance();
        mdatabasereference=mfirebasedatabase.getReference();
        auth= FirebaseAuth.getInstance();
    }

    private void escuchadorfirebase() {
        correo=auth.getCurrentUser().getUid();
        mdatabasereference.child(correo).child("sala").child("focos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Toast.makeText(getApplicationContext(),"texto: "+dataSnapshot,Toast.LENGTH_LONG).show();
                try {
                    if(listData2.size()>0)
                        listData2.clear();
                    for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                        Data2 data2 = postSnapshot.getValue(Data2.class);
                        listData2.add(data2);
                        recyclerView2.setAdapter(adaptador2);}

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"error "+e,Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    private void intDada() {

        listData.add(new Data(R.drawable.ic_carro,"carro"));
        listData.add(new Data(R.drawable.ic_copo_nieve,"nieve"));
        listData.add(new Data(R.drawable.ic_foco_apagado,"foco apagado"));
        listData.add(new Data(R.drawable.ic_foco_prendido,"foco prendido"));
        listData.add(new Data(R.drawable.ic_foco_apagado2,"apagado dos"));
        listData.add(new Data(R.drawable.ic_foco_prendido2,"prendido 2"));
        listData.add(new Data(R.drawable.ic_carro,"carro"));
        listData.add(new Data(R.drawable.ic_foco_prendido2,"andi"));

        listData.add(new Data(R.drawable.ic_carro,"carro"));
        listData.add(new Data(R.drawable.ic_copo_nieve,"nieve"));
        listData.add(new Data(R.drawable.ic_foco_apagado,"foco apagado"));
        listData.add(new Data(R.drawable.ic_foco_prendido,"foco prendido"));
        listData.add(new Data(R.drawable.ic_foco_apagado2,"apagado dos"));
        listData.add(new Data(R.drawable.ic_foco_prendido2,"prendido 2"));
        listData.add(new Data(R.drawable.ic_carro,"carro"));


        listData.add(new Data(R.drawable.ic_carro,"carro"));
        listData.add(new Data(R.drawable.ic_copo_nieve,"nieve"));
        listData.add(new Data(R.drawable.ic_foco_apagado,"foco apagado"));
        listData.add(new Data(R.drawable.ic_foco_prendido,"foco prendido"));
        listData.add(new Data(R.drawable.ic_foco_apagado2,"apagado dos"));
        listData.add(new Data(R.drawable.ic_foco_prendido2,"prendido 2"));
        listData.add(new Data(R.drawable.ic_carro,"carro"));



    }


}
