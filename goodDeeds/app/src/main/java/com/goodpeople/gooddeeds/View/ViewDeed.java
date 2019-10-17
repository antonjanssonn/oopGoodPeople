package com.goodpeople.gooddeeds.View;

import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.goodpeople.gooddeeds.Model.Entities.IDeed;
import com.goodpeople.gooddeeds.R;


public class ViewDeed extends ViewTemplate {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.view_deed);
        super.onCreate(savedInstanceState);
        IDeed deed = deedController.getCurrentDeedHandler();
        TextView subject = findViewById(R.id.deedSubject);
        subject.setText(deed.getSubject());
        TextView description = findViewById(R.id.deedDescription);
        description.setText(deed.getDescription());
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        if (!deedController.isDeedOwnerHandler(deed))
            deleteButton.setVisibility(View.GONE);
    }

    public void deleteCurrentDeed(View view) {
        deedController.deleteCurrentDeedHandler();
        finish();
    }
}
