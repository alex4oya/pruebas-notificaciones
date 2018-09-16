package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.servicios;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.R;

public class notificacion extends Service {
    FirebaseAuth auth;
    public  static  final int NOTIFICACION_ID=1;
    String mensaje,id;
    DatabaseReference mdatabasereference = FirebaseDatabase.getInstance().getReference();


    public void onCreate(){
        super.onCreate();
        auth= FirebaseAuth.getInstance();
        id=auth.getCurrentUser().getUid();
        DatabaseReference texto =mdatabasereference.child(id).child("sala").child("focos").child("1").child("dato");
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

    public int onStartCommand(Intent intent,int flag,int starId){

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
