package com.example.finalpab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

    }
    public void p1(View view) {
        Intent intent = new Intent(Home.this, Data.class);
        startActivity(intent);
    }
    public void p2(View view) {
        Intent intent = new Intent(Home.this, Berita.class);
        startActivity(intent);
    }
    public void p3(View view) {
        Intent intent = new Intent(Home.this, Sosmed.class);
        startActivity(intent);
    }
    public void p4(View view) {
        Intent intent = new Intent(Home.this, About.class);
        startActivity(intent);
    }
}