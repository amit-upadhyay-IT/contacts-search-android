package com.amitupadhyay.displaycontacts.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by aupadhyay on 5/8/17.
 */

public class Contact implements Parcelable {
    public String id;
    public String name;
    public ArrayList<ContactEmail> emails;
    public ArrayList<ContactPhone> numbers;

    public Contact(String id, String name) {
        this.id = id;
        this.name = name;
        this.emails = new ArrayList<ContactEmail>();
        this.numbers = new ArrayList<ContactPhone>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ContactEmail> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<ContactEmail> emails) {
        this.emails = emails;
    }

    public ArrayList<ContactPhone> getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList<ContactPhone> numbers) {
        this.numbers = numbers;
    }

    public Contact()
    {

    }

    private Contact(Parcel in)
    {
        id = in.readString();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public String toString() {
        String result = name;
        if (numbers.size() > 0) {
            ContactPhone number = numbers.get(0);
            result += " (" + number.number + " - " + number.type + ")";
        }
        if (emails.size() > 0) {
            ContactEmail email = emails.get(0);
            result += " [" + email.address + " - " + email.type + "]";
        }
        return result;
    }

    public void addEmail(String address, String type) {
        emails.add(new ContactEmail(address, type));
    }

    public void addNumber(String number, String type) {
        numbers.add(new ContactPhone(number, type));
    }
}

