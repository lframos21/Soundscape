package com.fic.soundscape.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fic.soundscape.news.NewsActivity;
import com.fic.soundscape.R;
import com.fic.soundscape.settings.SettingsActivity;
import com.fic.soundscape.blogs.BlogsActivity;
import com.fic.soundscape.chart.ChartActivity;

public class HomeActivity extends AppCompatActivity {

    private Button btn_news;
    private Button btn_chart;
    private Button btn_blogs;
    private Button btn_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        //Ir a Noticias
        btn_news = findViewById(R.id.home_button_news);
        btn_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });

        //Ir a Chart
        btn_chart = findViewById(R.id.home_button_chart);
        btn_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ChartActivity.class);
                startActivity(intent);
            }
        });

        //Ir a Blogs
        btn_blogs = findViewById(R.id.home_button_blogs);
        btn_blogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BlogsActivity.class);
                startActivity(intent);
            }
        });

        //Ir a Configuracion
        btn_settings = findViewById(R.id.home_button_settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}