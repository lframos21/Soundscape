package com.fic.soundscape.chart;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.soundscape.R;
import com.fic.soundscape.data.Api_Service;
import com.fic.soundscape.data.Retrofit_Instance;
import com.fic.soundscape.domain.Chart_Interactor;
import com.fic.soundscape.domain.Chart_Repository;
import com.fic.soundscape.domain.Users_Interactor;
import com.fic.soundscape.domain.Users_Repository;
import com.fic.soundscape.model.Chart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartActivity extends AppCompatActivity {

    RecyclerView rv;
    RecyclerView.Adapter rva;
    RecyclerView.LayoutManager lm;
    List<Chart> charts = new ArrayList<>();
    private Chart_Interactor chart_interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chart);

        Api_Service api_service = Retrofit_Instance.getRetrofitInstance().create(Api_Service.class);
        chart_interactor = new Chart_Interactor(new Chart_Repository(api_service));

        // Enlazamos la interfaz gráfica del RecyclerView con el código
        rv = findViewById(R.id.chart_rv);
        // Establecemos que los elementos del RecyclerView se apilen verticalmente
        lm = new LinearLayoutManager(this);
        // Creamos un adaptador para el RecyclerView
        rva = new ChartAdapter(this, charts);
        // Enlazamos el adaptador con el objeto RecyclerView
        rv.setAdapter(rva);
        rv.setLayoutManager(lm);

        //Obtener Canciones
        getsongs();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void getsongs() {
        // Hacer la solicitud GET
        chart_interactor.getSongs(new Callback<List<Chart>>() {
            @Override
            public void onResponse(Call<List<Chart>> call, Response<List<Chart>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        charts.clear();  // Limpiar la lista existente
                        charts.addAll(response.body());  // Agregar las canciones a la lista
                        rva.notifyDataSetChanged(); // Notifica al adaptador que los datos cambiaron
                    } else {
                        Log.e("Error", "La respuesta está vacía.");
                    }

                } else {
                    // Manejo de errores HTTP (respuestas no exitosas)
                    Log.e("Error", "Código de error: " + response.code());
                }
            }

            public void onFailure(Call<List<Chart>> call, Throwable t) {
                // Manejo de errores de red u otros fallos
                if (t instanceof IOException) {
                    Log.e("Error", "Fallo en la conexión de red: " + t.getMessage());
                } else {
                    Log.e("Error", "Error desconocido: " + t.getMessage());
                }
            }
        });
    }
}