package com.fic.soundscape.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fic.soundscape.R;
import com.fic.soundscape.data.Api_Service;
import com.fic.soundscape.data.Retrofit_Instance;
import com.fic.soundscape.domain.Users_Interactor;
import com.fic.soundscape.domain.Users_Repository;
import com.fic.soundscape.home.HomeActivity;
import com.fic.soundscape.login.LoginActivity;
import com.fic.soundscape.model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etename;
    private EditText etemail;
    private EditText etpass;
    private Button btn_register;
    private Button btn_cancel;
    private Users_Interactor users_interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        etename = findViewById(R.id.register_edittext_name);
        etemail = findViewById(R.id.register_edittext_email);
        etpass = findViewById(R.id.register_edittext_password);

        //Register
        btn_register = findViewById(R.id.register_button_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

        Api_Service api_service = Retrofit_Instance.getRetrofitInstance().create(Api_Service.class);
        users_interactor = new Users_Interactor(new Users_Repository(api_service));

        //Go back to login activity
        btn_cancel = findViewById(R.id.register_button_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Register(){

        String nombre = etename.getText().toString().trim();
        String email = etemail.getText().toString().trim();
        String contraseña = etpass.getText().toString().trim();

        if (TextUtils.isEmpty(nombre)) {
            etename.setError("Ingrese Nombre");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            etemail.setError("Ingrese Email");
            return;
        }

        if (TextUtils.isEmpty(contraseña)) {
            etpass.setError("Ingrese Contraseña");
            return;
        }

        Users nuevoUsers = new Users();

        nuevoUsers.setNombre(nombre);
        nuevoUsers.setEmail(email);
        nuevoUsers.setContraseña(contraseña);

        users_interactor.postUsers(nuevoUsers, new  Callback<Users>(){

            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Error al Registrar los Datos", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Conexion fallida", Toast.LENGTH_SHORT).show();
            }
        });

    }

}