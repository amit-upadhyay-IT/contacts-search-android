package com.amitupadhyay.displaycontacts;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsRV = (RecyclerView) findViewById(R.id.contactsRecyclerView);
        contactsList = new ContactFetcher(this).fetchAll();
        ContactsRecyclerAdapter recyclerAdapter = new ContactsRecyclerAdapter(contactsList, MainActivity.this, R.layout.adapter_contact_item);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        contactsRV.setLayoutManager(llm);
        contactsRV.setAdapter(recyclerAdapter);
    }
}
