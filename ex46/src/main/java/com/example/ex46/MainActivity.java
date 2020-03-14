package com.example.ex46;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    CheckBox cb1,cb2,cb3;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        login = findViewById(R.id.btn_login);
    }

    public void onclick(View v) {
        String text = "";
        if (cb1.isChecked()) {
            text+=cb1.getText().toString();
            text += "\n";
        }
        if (cb2.isChecked()) {
            text+=cb2.getText().toString();
            text += "\n";
        }
        if (cb3.isChecked()) {
            text+=cb3.getText().toString();
        }
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
