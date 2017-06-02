package com.amitupadhyay.displaycontacts.search.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.amitupadhyay.displaycontacts.utils.ContactEmail;
import com.amitupadhyay.displaycontacts.utils.ContactPhone;

import java.util.ArrayList;

/**
 * Created by aupadhyay on 5/31/17.
 */

public class ContactsWrapper implements Parcelable {

    public String id;
    public String name;
    public ArrayList<ContactEmail> emails;
    public ArrayList<ContactPhone> numbers;

    public ContactsWrapper(String id, String name, ArrayList<ContactEmail> emails, ArrayList<ContactPhone> numbers) {
        this.id = id;
        this.name = name;
        this.emails = emails;
        this.numbers = numbers;
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

    public ContactsWrapper() {
    }

    private ContactsWrapper(Parcel in)
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

    public static final Creator<ContactsWrapper> CREATOR = new Creator<ContactsWrapper>() {
        @Override
        public ContactsWrapper createFromParcel(Parcel in) {
            return new ContactsWrapper(in);
        }

        @Override
        public ContactsWrapper[] newArray(int size) {
            return new ContactsWrapper[size];
        }
    };
}
