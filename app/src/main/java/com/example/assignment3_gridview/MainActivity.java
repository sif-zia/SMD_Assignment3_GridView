package com.example.assignment3_gridview;

import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ArrayList<MenuOption> menuOptions = new ArrayList<>();
        menuOptions.add(new MenuOption(R.drawable.camera, "Camera"));
        menuOptions.add(new MenuOption(R.drawable.camera_roll, "Camera Roll"));
        menuOptions.add(new MenuOption(R.drawable.featured, "Featured"));
        menuOptions.add(new MenuOption(R.drawable.my_videos, "My Videos"));
        menuOptions.add(new MenuOption(R.drawable.likes, "Likes"));
        menuOptions.add(new MenuOption(R.drawable.watch_later, "Watch Later"));
        menuOptions.add(new MenuOption(R.drawable.stats, "Stats"));
        menuOptions.add(new MenuOption(R.drawable.subscriptions, "Subscriptions"));
        menuOptions.add(new MenuOption(R.drawable.help, "Help"));

        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, menuOptions);
        ((GridView) findViewById(R.id.grid_view)).setAdapter(customAdapter);
    }
}