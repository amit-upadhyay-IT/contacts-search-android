package com.amitupadhyay.displaycontacts.search.data;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * Created by aupadhyay on 5/31/17.
 */

public class ContactsSuggestion implements SearchSuggestion {

    private String mContactName;
    private boolean mIsHistory = false;

    public ContactsSuggestion(String suggestion) {
        this.mContactName= suggestion.toLowerCase();
    }

    public ContactsSuggestion(Parcel source) {
        this.mContactName= source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory() {
        return this.mIsHistory;
    }

    @Override
    public String getBody() {
        return mContactName;
    }

    public static final Creator<ContactsSuggestion> CREATOR = new Creator<ContactsSuggestion>() {
        @Override
        public ContactsSuggestion createFromParcel(Parcel in) {
            return new ContactsSuggestion(in);
        }

        @Override
        public ContactsSuggestion[] newArray(int size) {
            return new ContactsSuggestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mContactName);
        dest.writeInt(mIsHistory ? 1 : 0);
    }
}

