package com.example.kuiz20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_launcher);
        Button launcherActivityNextButton = findViewById(R.id.launcherActivityNextButton);
        EditText nameEditText = findViewById(R.id.nameEditText);

        launcherActivityNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LauncherActivity.this, KuizActivity.class);
                intent.putExtra(Extras.NAME, nameEditText.getText().toString());
                startActivity(intent);
            }
        });
    }
}