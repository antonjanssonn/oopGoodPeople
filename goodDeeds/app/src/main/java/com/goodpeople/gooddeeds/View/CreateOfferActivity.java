package com.goodpeople.gooddeeds.View;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.goodpeople.gooddeeds.Controller.DeedController;
import com.goodpeople.gooddeeds.Model.GoodDeeds;
import com.goodpeople.gooddeeds.R;

import java.util.UUID;

public class CreateOfferActivity extends AppCompatActivity {

    UUID id = UUID.randomUUID();
    private DeedController deedController = new DeedController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createoffer);

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText subjectEditText = findViewById(R.id.subjectEditText);
                EditText descriptionEditText = findViewById(R.id.descriptionEditText);

                deedController.addOffer(id, subjectEditText.toString(), descriptionEditText.toString());
            }
        });
    }



}