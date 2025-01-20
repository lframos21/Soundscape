package com.fic.soundscape.login;

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
import androidx.fragment.app.FragmentTransaction;

import com.fic.soundscape.R;
import com.fic.soundscape.data.Api_Service;
import com.fic.soundscape.data.Retrofit_Instance;
import com.fic.soundscape.domain.Users_Interactor;
import com.fic.soundscape.domain.Users_Repository;
import com.fic.soundscape.model.Users;
import com.fic.soundscape.register.RegisterActivity;
import com.fic.soundscape.home.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etemail;
    private EditText etpass;
    private Button btn_login;
    private Button btn_register;
    private Users_Interactor users_interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        etemail = findViewById(R.id.login_edittext_email);
        etpass = findViewById(R.id.login_edittext_password);

        //Login
        btn_login = findViewById(R.id.login_button_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        Api_Service api_service = Retrofit_Instance.getRetrofitInstance().create(Api_Service.class);
        users_interactor = new Users_Interactor(new Users_Repository(api_service));

        //Go to register activity
        btn_register = findViewById(R.id.login_button_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Login(){

        String email = etemail.getText().toString().trim();
        String contraseña = etpass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            etemail.setError("Ingrese email");
            return;
        }

        if (TextUtils.isEmpty(contraseña)) {
            etpass.setError("Ingrese contraseña");
            return;
        }

        Users nuevoUsers = new Users();

        nuevoUsers.setEmail(email);
        nuevoUsers.setContraseña(contraseña);

        users_interactor.login(nuevoUsers, new  Callback<Users>(){

            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Inicio de Sesion Exitoso", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Email o contraseña Incorrectos", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Conexion fallida", Toast.LENGTH_SHORT).show();
            }
        });

    }

}