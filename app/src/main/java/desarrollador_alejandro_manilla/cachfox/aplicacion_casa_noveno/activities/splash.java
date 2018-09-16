package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.R;

public class splash extends AppCompatActivity {
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        countDownTimer=new CountDownTimer(2000,500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent hola=new Intent(splash.this,login.class);
                startActivity(hola);


            }
        }.start();
        //
    }
}
