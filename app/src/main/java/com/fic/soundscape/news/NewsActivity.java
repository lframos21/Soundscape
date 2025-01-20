package com.fic.soundscape.news;

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
import com.fic.soundscape.blogs.BlogsAdapter;
import com.fic.soundscape.data.Api_Service;
import com.fic.soundscape.data.Retrofit_Instance;
import com.fic.soundscape.domain.Blogs_Interactor;
import com.fic.soundscape.domain.Blogs_Repository;
import com.fic.soundscape.domain.News_Interactor;
import com.fic.soundscape.domain.News_Repository;
import com.fic.soundscape.model.Blog;
import com.fic.soundscape.model.New;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    RecyclerView rv;
    RecyclerView.Adapter rva;
    RecyclerView.LayoutManager lm;
    List<New> news = new ArrayList<>();
    private News_Interactor news_interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news);

        Api_Service api_service = Retrofit_Instance.getRetrofitInstance().create(Api_Service.class);
        news_interactor = new News_Interactor(new News_Repository(api_service));

        // Enlazamos la interfaz gráfica del RecyclerView con el código
        rv = findViewById(R.id.news_rv);
        // Establecemos que los elementos del RecyclerView se apilen verticalmente
        lm = new LinearLayoutManager(this);
        // Creamos un adaptador para el RecyclerView
        rva = new NewsAdapter(this, news);
        // Enlazamos el adaptador con el objeto RecyclerView
        rv.setAdapter(rva);
        rv.setLayoutManager(lm);

        //Obtener Canciones
        getnews();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void getnews() {
        // Hacer la solicitud GET
        news_interactor.getNews(new Callback<List<New>>() {
            @Override
            public void onResponse(Call<List<New>> call, Response<List<New>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        news.clear();  // Limpiar la lista existente
                        news.addAll(response.body());  // Agregar las canciones a la lista
                        rva.notifyDataSetChanged(); // Notifica al adaptador que los datos cambiaron
                    } else {
                        Log.e("Error", "La respuesta está vacía.");
                    }

                } else {
                    // Manejo de errores HTTP (respuestas no exitosas)
                    Log.e("Error", "Código de error: " + response.code());
                }
            }

            public void onFailure(Call<List<New>> call, Throwable t) {
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