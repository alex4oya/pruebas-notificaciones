package desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.R;
import desarrollador_alejandro_manilla.cachfox.aplicacion_casa_noveno.pruebas.prueba_grid;

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText email,contraseña;
    Button ingresar;
    TextView passforget,registrar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.email);
        contraseña=(EditText)findViewById(R.id.contraseña);
        ingresar=(Button)findViewById(R.id.ingresar);
        passforget=(TextView)findViewById(R.id.passforget);
        registrar=(TextView)findViewById(R.id.registrar);
        ////////////////////////////////////////////////////////////7
        passforget.setOnClickListener(this);
        ingresar.setOnClickListener(this);
        registrar.setOnClickListener(this);
        ///////////////////////////////
        auth= FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){//si  esta logeado manda a la pantalla de validacion
            Intent cambio=new Intent(login.this,prueba_grid.class);
            startActivity(cambio);
            finish();
        }
    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ingresar:
                logiuser(email.getText().toString(),contraseña.getText().toString());
                break;
            case R.id.registrar:
                startActivity(new Intent(login.this,register.class));
                finish();
        }
    }
    private void logiuser(String Email, final String password) {
        auth.signInWithEmailAndPassword(Email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            if (password.length()<8){
                                Toast.makeText(getBaseContext(), "la contraseña debe ser mayor a 8 caracteres", Toast.LENGTH_LONG).show();

                            }else{
                                startActivity(new Intent(login.this,prueba_grid.class));
                                finish();
                            }
                        }
                    }
                });
    }
}
