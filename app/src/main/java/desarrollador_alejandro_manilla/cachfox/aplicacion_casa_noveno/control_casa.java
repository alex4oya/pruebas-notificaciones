package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno;

import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class control_casa extends AppCompatActivity {
    public  static  final int NOTIFICACION_ID=1;
    DatabaseReference mdatabasereference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference texto =mdatabasereference.child("texto1");
    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_casa);



        texto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notificacion();
                mensaje=dataSnapshot.getValue().toString();

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
}
