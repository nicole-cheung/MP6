package com.example.nicolecheung.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Button button = (Button)findViewById(R.id.button1);
    button.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
            Intent i = new Intent(this, ContactPage.class);
            startActivity(i);
        }
    }
}
