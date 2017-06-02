package com.amitupadhyay.displaycontacts.search.data;

import android.content.Context;
import android.widget.Filter;

import com.amitupadhyay.displaycontacts.search.util.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by aupadhyay on 5/31/17.
 */

public class DataHelper {

    private static String contactsName[] = new String[Constants.mContactList.size()];// making array to store the contacts name only

    private static List<ContactsSuggestion> setNamesIntoContactsSuggestion()
    {
        for (int i = 0; i < Constants.mContactList.size(); ++i)
        {
            contactsName[i] = Constants.mContactList.get(i).name;
        }

        ArrayList<ContactsSuggestion> theContactSuggestion = new ArrayList<>(Constants.mContactList.size());

        for (int i = 0; i < Constants.mContactList.size(); ++i)
        {
            theContactSuggestion.add(new ContactsSuggestion(contactsName[i]));
        }
        return theContactSuggestion;
    }

    private static List<ContactsWrapper> sContactWrappers = new ArrayList<>();

    private static List<ContactsSuggestion> sContactsSuggestion = setNamesIntoContactsSuggestion();

    public interface OnFindContactsListener {
        void onResults(List<ContactsWrapper> results);
    }

    public interface OnFindSuggestionsListener {
        void onResults(List<ContactsSuggestion> results);
    }

    public static List<ContactsSuggestion> getHistory(Context context, int count) {

        List<ContactsSuggestion> suggestionList = new ArrayList<>();
        ContactsSuggestion contactsSuggestion;
        for (int i = 0; i < sContactsSuggestion.size(); i++) {
            contactsSuggestion = sContactsSuggestion.get(i);
            contactsSuggestion.setIsHistory(true);
            suggestionList.add(contactsSuggestion);
            if (suggestionList.size() == count) {
                break;
            }
        }
        return suggestionList;
    }

    public static void resetSuggestionsHistory() {
        for (ContactsSuggestion contactsSuggestion : sContactsSuggestion) {
            contactsSuggestion.setIsHistory(false);
        }
    }

    public static void findSuggestions(Context context, String query, final int limit, final long simulatedDelay,
                                       final OnFindSuggestionsListener listener) {
        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                try {
                    Thread.sleep(simulatedDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                DataHelper.resetSuggestionsHistory();
                List<ContactsSuggestion> suggestionList = new ArrayList<>();
                if (!(constraint == null || constraint.length() == 0)) {

                    for (ContactsSuggestion suggestion : sContactsSuggestion) {
                        if (suggestion.getBody().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(suggestion);
                            if (limit != -1 && suggestionList.size() == limit) {
                                break;
                            }
                        }
                    }
                }

                FilterResults results = new FilterResults();
                Collections.sort(suggestionList, new Comparator<ContactsSuggestion>() {
                    @Override
                    public int compare(ContactsSuggestion lhs, ContactsSuggestion rhs) {
                        return lhs.getIsHistory() ? -1 : 0;
                    }
                });
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<ContactsSuggestion>) results.values);
                }
            }
        }.filter(query);

    }

    public static void findContacts(Context context, String query, final OnFindContactsListener listener) {
        initColorWrapperList(context);

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {


                List<ContactsWrapper> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (ContactsWrapper color : sContactWrappers) {
                        if (color.getName().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(color);
                        }
                    }

                }

                FilterResults results = new FilterResults();
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<ContactsWrapper>) results.values);
                }
            }
        }.filter(query);

    }


    private static void initColorWrapperList(Context context) {

        if (sContactWrappers.isEmpty()) {

            // fill contactWrapper using Constants.
            for (int i = 0; i < Constants.mContactList.size(); ++i)
            {
                sContactWrappers.get(i).id = Constants.mContactList.get(i).id;
                sContactWrappers.get(i).name = Constants.mContactList.get(i).name;
                sContactWrappers.get(i).emails = Constants.mContactList.get(i).emails;
                sContactWrappers.get(i).numbers = Constants.mContactList.get(i).numbers;
            }
        }
    }
}
