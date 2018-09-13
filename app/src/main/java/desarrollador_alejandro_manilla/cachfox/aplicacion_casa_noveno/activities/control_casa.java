package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.activities;

import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.R;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.servicios.notificacion;

public class control_casa extends AppCompatActivity {
    DatabaseReference mdatabasereference = FirebaseDatabase.getInstance().getReference();
    String mensaje;
    TextView texto1;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_casa);
        texto1=(TextView)findViewById(R.id.texto1);
        auth= FirebaseAuth.getInstance();
        verificacion();
        Intent servicio=new Intent(control_casa.this,notificacion.class);
        startService(servicio);

    }

    void verificacion (){
        auth.getCurrentUser()
                .reload()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(auth.getCurrentUser().isEmailVerified()==true){


                        }else{
                            Toast.makeText(getApplicationContext(),"verifique el correo "+"\n"+auth.getCurrentUser().getEmail(),Toast
                            .LENGTH_LONG).show();
                            finish();
                        }
                    }
                });
    }
}
