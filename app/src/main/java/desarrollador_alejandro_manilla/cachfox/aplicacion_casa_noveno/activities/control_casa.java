package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.activities;

import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.adaptadores.adaptador_sala_focos;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.adaptadores.vista_sala_focos;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.servicios.notificacion;

public class control_casa extends AppCompatActivity {
    private FirebaseDatabase mfirebasedatabase;
    DatabaseReference mdatabasereference;
    ListView lista_sala_focos;
    String mensaje;
    TextView texto1;
    FirebaseAuth auth;
    String idUsuario;

    private List<vista_sala_focos> Lista = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_casa);

        auth = FirebaseAuth.getInstance();
        idUsuario = auth.getUid();
        verificacion();
        Intent servicio = new Intent(control_casa.this, notificacion.class);
        startService(servicio);

    }

    void verificacion() {
        auth.getCurrentUser()
                .reload()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (auth.getCurrentUser().isEmailVerified() == true) {


                        } else {
                            Toast.makeText(getApplicationContext(), "verifique el correo " + "\n" + auth.getCurrentUser().getEmail(), Toast
                                    .LENGTH_LONG).show();
                            finish();
                        }
                    }
                });
    }

    private void firebase() {
        FirebaseApp.initializeApp(this);
        mfirebasedatabase = FirebaseDatabase.getInstance();
        mdatabasereference = mfirebasedatabase.getReference();
    }

    void vistaSala() {

    }

    private void escuchadorfirebase() {
        lista_sala_focos.setVisibility(View.INVISIBLE);
        mdatabasereference.child(idUsuario).child("sala").child("focos").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                //Toast.makeText(getBaseContext(),""+t,Toast.LENGTH_LONG).show();
                if (Lista.size() > 0)
                    Lista.clear();
                try {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        vista_sala_focos mdatosBasicos = postSnapshot.getValue(vista_sala_focos.class);

                        Lista.add(mdatosBasicos);
                    }
                } catch (Exception e) {

                    Toast.makeText(getBaseContext(), "" + e, Toast.LENGTH_LONG).show();
                }
                adaptador_sala_focos adaptadorDatosBasicos = new adaptador_sala_focos(control_casa.this, Lista);
                lista_sala_focos.setAdapter(adaptadorDatosBasicos);
                lista_sala_focos.setVisibility(View.VISIBLE);
                if (Lista.size() < 1) {
                    Toast.makeText(getApplicationContext(), "No se encontro su dispositivo, revise la conexion del dispositivo conectado al circuito",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
