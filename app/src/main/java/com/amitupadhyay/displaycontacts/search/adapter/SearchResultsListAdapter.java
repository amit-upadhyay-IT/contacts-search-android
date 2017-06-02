package com.amitupadhyay.displaycontacts.search.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.amitupadhyay.displaycontacts.R;
import com.amitupadhyay.displaycontacts.utils.Contact;
import com.arlib.floatingsearchview.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aupadhyay on 5/31/17.
 */

public class SearchResultsListAdapter extends RecyclerView.Adapter<SearchResultsListAdapter.ViewHolder> {

    private Context context;

    public SearchResultsListAdapter(int[] colors_set, Context context) {
        this.context = context;
    }

    public SearchResultsListAdapter() {
    }

    private List<Contact> mDataSet = new ArrayList<>();

    private int mLastAnimatedItemPosition = -1;

    public interface OnItemClickListener{
        void onClick(Contact colorWrapper);
    }

    private OnItemClickListener mItemsOnClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mContactName;
        public final TextView mContactNumber;
        public final TextView mEmail;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mContactName = (TextView) view.findViewById(R.id.tvName);
            mContactNumber = (TextView) view.findViewById(R.id.tvPhone);
            mEmail = (TextView) view.findViewById(R.id.tvEmail);
            mView = view;
        }
    }

    public void swapData(List<Contact> mNewDataSet) {
        mDataSet = mNewDataSet;
        notifyDataSetChanged();
    }

    public void setItemsOnClickListener(OnItemClickListener onClickListener){
        this.mItemsOnClickListener = onClickListener;
    }

    @Override
    public SearchResultsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultsListAdapter.ViewHolder holder, final int position) {

        final Contact contactSuggestion = mDataSet.get(position);
        holder.mContactName.setText(contactSuggestion.getName());
        holder.mContactNumber.setText(contactSuggestion.getNumbers().toString());
        holder.mEmail.setText(contactSuggestion.getEmails().toString());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, contactSuggestion.getName(), Toast.LENGTH_SHORT).show();

            }
        });


        if(mLastAnimatedItemPosition < position){
            animateItem(holder.itemView);
            mLastAnimatedItemPosition = position;
        }

        if(mItemsOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemsOnClickListener.onClick(mDataSet.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    private void animateItem(View view) {
        view.setTranslationY(Util.getScreenHeight((Activity) view.getContext()));
        view.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(700)
                .start();
    }
}

