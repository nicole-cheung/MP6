package com.example.nicolecheung.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;

public class ContactPage extends AppCompatActivity {

    private static final String TAG = "ContactPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);

        final Button saveBtn = (Button) findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Log.i(TAG, "20 Points Plz");
                EditText a = findViewById(R.id.nameField);
                EditText b = findViewById(R.id.phoneField);
                EditText c = findViewById(R.id.monthField);
                EditText d = findViewById(R.id.dayField);
                EditText e = findViewById(R.id.msgField);
                EditText f = findViewById(R.id.timeField);
                String name = a.getText().toString();
                String phone = b.getText().toString();
                String g = c.getText().toString();
                int month = Integer.parseInt(g);
                String h = d.getText().toString();
                int day = Integer.parseInt(h);
                String msg = e.getText().toString();
                String i = f.getText().toString();
                int time = Integer.parseInt(i);
                storeContact(name, phone, month, day, msg, time);
                startActivity(new Intent(ContactPage.this, MainActivity.class));
            }
        });
    }
    public void storeContact(String name, String phone, int month, int day, String message, int time) {
        SharedPreferences contact = getSharedPreferences(name, 0);
        SharedPreferences.Editor save = contact.edit();
        save.putString("Name", name);
        save.putString("Phone", phone);
        save.putInt("Month", month);
        save.putInt("Day", day);
        save.putString("Message", message);
        save.putInt("Time", time);
        save.apply();
    }
}