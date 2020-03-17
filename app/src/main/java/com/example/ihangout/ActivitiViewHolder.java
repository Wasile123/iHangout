package com.example.ihangout;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;


public class ActivitiViewHolder extends RecyclerView.ViewHolder {

    private View view;
    public Button joinButton;

    public ActivitiViewHolder(View view) {
        super(view);
        this.view = view;
    }

    public void setTitle(String title) {
        TextView textView = (TextView) view.findViewById(R.id.titleTextView);
        textView.setText(title);
    }

    public void setName(String name) {
        TextView textView = view.findViewById(R.id.nameTextView);
        textView.setText(name);
    }

    public void setCity(String city) {
        TextView textView = view.findViewById(R.id.cityTextView);
        textView.setText(city);
    }

    public void setAddress(String address) {
        TextView textView = view.findViewById(R.id.addressTextview);
        textView.setText(address);
    }

    public void setPhoneNumber(String phoneNr) {
        TextView textView = view.findViewById(R.id.phoneTextview);
        textView.setText(phoneNr);
    }

    public void setDate(String date) {
        TextView textView = view.findViewById(R.id.dateTextview);
        textView.setText(date);
    }

    public void setPeople(String people) {
        TextView textView = view.findViewById(R.id.peopleTextView);
        textView.setText(people);
    }

    public Button getJoinButton() {
        joinButton = view.findViewById(R.id.joinButton);
        return joinButton;
    }


}
