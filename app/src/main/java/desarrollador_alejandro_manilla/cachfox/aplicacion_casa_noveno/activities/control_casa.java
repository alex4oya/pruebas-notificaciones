package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.activities;

import android.app.NotificationManager;
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

public class control_casa extends AppCompatActivity {
    public  static  final int NOTIFICACION_ID=1;
    DatabaseReference mdatabasereference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference texto =mdatabasereference.child("texto1");
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
//esto de el texto activa la notificacion, una ves que se cambie
// el dato en la base de datos guarda el string en mensaje y lo lanza en la notificacion
        //quiero hacer el seervicio que obtenga el dato aunque la app este cerrrada
        //y lance la notificacion
        texto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mensaje=dataSnapshot.getValue().toString();
                notificacion();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void notificacion(){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("el nuevo mensaje dice")
                .setContentText(""+mensaje)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        NotificationManager notificacion=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificacion.notify(NOTIFICACION_ID,builder.build());
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
