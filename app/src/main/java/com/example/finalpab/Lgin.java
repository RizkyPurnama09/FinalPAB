package com.example.finalpab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lgin extends AppCompatActivity {
    private EditText edt1, edt2;
    private Button btn;

    private String ussername = "admin";
    private String password = "123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lgin);
        edt1=findViewById(R.id.edt1);
        edt2= findViewById(R.id.edt2);
        btn = findViewById(R.id.btn);

          btn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if (edt1.getText().toString().equalsIgnoreCase(ussername) && edt2.getText().toString().equalsIgnoreCase(password)) {
                      Intent login = new Intent(Lgin.this, Home.class);
                      startActivity(login);
                      Toast.makeText(Lgin.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                  } else {
                      Toast.makeText(Lgin.this, "Email atau Password Salah", Toast.LENGTH_SHORT).show();
                  }
              }
          });

    }

}