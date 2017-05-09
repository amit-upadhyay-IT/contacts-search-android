package com.amitupadhyay.displaycontacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.amitupadhyay.displaycontacts.utils.Contact;
import com.amitupadhyay.displaycontacts.utils.ContactFetcher;
import com.amitupadhyay.displaycontacts.utils.ContactsRecyclerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView contactsRV;
    ArrayList<Contact> contactsList;

    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        else
        {
            displayContactsList();
        }
    }

    private void displayContactsList()
    {
        contactsRV = (RecyclerView) findViewById(R.id.contactsRecyclerView);
        contactsList = new ContactFetcher(this).fetchAll();
        ContactsRecyclerAdapter recyclerAdapter = new ContactsRecyclerAdapter(contactsList, MainActivity.this, R.layout.adapter_contact_item);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        contactsRV.setLayoutManager(llm);
        contactsRV.setAdapter(recyclerAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    displayContactsList();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission or ask for permission again.
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}