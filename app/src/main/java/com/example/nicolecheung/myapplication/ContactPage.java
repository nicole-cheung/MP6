package com.example.nicolecheung.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class ContactPage extends AppCompatActivity {
    private static ArrayList<String> contactList = new ArrayList<>(1);
    public SharedPreferences data;
    private static final String TAG = "ContactPage";
    Timer bday = new Timer();

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
                String name = a.getText().toString();
                String phone = b.getText().toString();
                String theMonth = c.getText().toString();
                int month = Integer.parseInt(theMonth);
                String theDay = d.getText().toString();
                int day = Integer.parseInt(theDay);
                String msg = e.getText().toString();
                ArrayList<ContactPage> contacts = new ArrayList<>(1);
                contacts.add(new ContactPage());
                storeContact(name, phone, month, day, msg);
                SendSMS();


               /* TimerTask too = new TimerTask() {
                    public void run() {
                        SendSMS();
                    }
                };

                Date toExecute = new Date(2018, data.getInt("Month", 12), data.getInt("Day", 11), data.getInt("Hour", 1), data.getInt("Minute", 22));
                bday.schedule(too, toExecute); */

                startActivity(new Intent(ContactPage.this, MainActivity.class));
            }
        });
    }
    public ContactPage(){
    }
    public void storeContact(String setName, String setPhone, int setMonth, int setDay, String setMessage) {
        data = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor save = data.edit();
        save.putString("Name", setName);
        save.putString("Phone", setPhone);
        save.putInt("Month", setMonth);
        save.putInt("Day", setDay);
        save.apply();
        save.putString("Message", setMessage + ": " + Long.toString(daysBetweenTwoDates()) + " days early!");
        save.apply();
        contactList.add(setName);
    }
    public static ArrayList<String> getContactList() {
        return contactList;
    }
    public void SendSMS() {
        if (ContextCompat.checkSelfPermission(ContactPage.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            SmsManager toSend = SmsManager.getDefault();
            toSend.sendTextMessage(data.getString("Phone", "5554"), null, data.getString("Message", ""), null, null);
        } else {
            ActivityCompat.requestPermissions(ContactPage.this, new String[]{Manifest.permission.SEND_SMS}, 1);
            SendSMS();
        }
    }
    public long daysBetweenTwoDates() {
        Calendar cal = Calendar.getInstance();
        Date firstDate = cal.getTime();

        Calendar cal2 = Calendar.getInstance();
        cal2.clear();
        cal2.set(2019, data.getInt("Month", 0) - 1, data.getInt("Day", 0));
        Date secondDate = cal2.getTime();

        long diff = secondDate.getTime() - firstDate.getTime();
        long days = diff / (1000*60*60*24);
        return days + 1;
    }
}