package com.example.kursach_02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    Button openRegisterFragmentButton, openVhodFragmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openRegisterFragmentButton = findViewById(R.id.open_register_fragment);
        openVhodFragmentButton = findViewById(R.id.open_vhod_fragment);

        openRegisterFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterFragment();
            }
        });

        openVhodFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVhodFragment();
            }
        });

        // Add the initial fragment
        if (savedInstanceState == null) {
            showRegisterFragment();
        }
    }

    private void showRegisterFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new RegisterFragment())
                .commit();
    }

    private void showVhodFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new VhodFragment())
                .commit();
    }
}
