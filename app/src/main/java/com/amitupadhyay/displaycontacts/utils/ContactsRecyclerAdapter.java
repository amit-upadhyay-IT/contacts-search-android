package com.amitupadhyay.displaycontacts.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amitupadhyay.displaycontacts.R;

import java.util.ArrayList;

/**
 * Created by aupadhyay on 5/8/17.
 */

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ContactsHolder> {

    private ArrayList<Contact> contacts;
    private Context context;
    private int resource;

    public ContactsRecyclerAdapter(ArrayList<Contact> contacts, Context context, int resource) {
        this.contacts = contacts;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public ContactsRecyclerAdapter.ContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View myView = LayoutInflater.from(context).inflate(resource, parent, false);


        return new ContactsHolder(myView);
    }

    @Override
    public void onBindViewHolder(ContactsHolder holder, int position) {

        // Get the data item
        Contact contact = contacts.get(position);
        holder.tvName.setText(contact.name);
        holder.tvEmail.setText("");
        holder.tvPhone.setText("");
        if (contact.emails.size() > 0 && contact.emails.get(0) != null) {
            holder.tvEmail.setText(contact.emails.get(0).address);
        }
        if (contact.numbers.size() > 0 && contact.numbers.get(0) != null) {
            holder.tvPhone.setText(contact.numbers.get(0).number);
        }

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactsHolder extends RecyclerView.ViewHolder
    {
        TextView tvName, tvEmail, tvPhone;

        public ContactsHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
            tvPhone = (TextView) itemView.findViewById(R.id.tvPhone);
        }
    }
}
