package com.example.nicolecheung.myapplication;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class ContactPage extends AppCompatActivity {
    private static ArrayList<String> contactList = new ArrayList<>(1);
    public SharedPreferences data;
    private static final String TAG = "ContactPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);

        final Button deleteBtn = (Button) findViewById(R.id.deleteButton);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data.edit().clear().apply();
                // Remove Listing
            }
        });

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
                ArrayList<ContactPage> contacts = new ArrayList<>(1);
                contacts.add(new ContactPage());
                storeContact(name, phone, month, day, msg, time);

                TimerTask toDo = new TimerTask() {
                    public void run() {
                        SmsManager toSend = SmsManager.getDefault();
                        toSend.sendTextMessage(data.getString("Phone", "6302075006"), null, data.getString("Message", ""), null, null);
                    }
                };

                Date toExecute = new Date(2018, data.getInt("Month", 1), data.getInt("Day", 1));

                Timer bday = new Timer(true);
                bday.schedule(toDo, toExecute);
                startActivity(new Intent(ContactPage.this, MainActivity.class));
            }
        });
    }
    public ContactPage(){
    }
    public void storeContact(String setName, String setPhone, int setMonth, int setDay, String setMessage, int setTime) {
        data = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor save = data.edit();
        save.putString("Name", setName);
        save.putString("Phone", setPhone);
        save.putInt("Month", setMonth);
        save.putInt("Day", setDay);
        save.putString("Message", setMessage);
        save.putInt("Time", setTime);
        save.apply();
        contactList.add(setName);
    }
    public static ArrayList<String> getContactList() {
        return contactList;
    }
}