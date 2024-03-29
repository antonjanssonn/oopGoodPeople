package com.goodpeople.gooddeeds.view.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.goodpeople.gooddeeds.model.entities.IAccount;
import com.goodpeople.gooddeeds.R;
import com.goodpeople.gooddeeds.view.ViewTemplate;

/**
 * Responsible for providing information about a users account.
 */

public class AccountDetails extends ViewTemplate {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.account_detail);
        super.onCreate(savedInstanceState);
        populateFields();
    }

    @Override
    public void onResume() {
        super.onResume();
        populateFields();
    }

    private void populateFields() {
        IAccount account = accountController.getLoggedInAccountHandler();
        TextView name = findViewById(R.id.account_name_data);
        TextView email = findViewById(R.id.account_email_data);
        TextView postalCode = findViewById(R.id.account_postal_code_data);
        name.setText(account.getName());
        email.setText(account.getEmail());
        postalCode.setText(Integer.toString(account.getPostalCode()));
    }

    public void editDetailsView(View view) {
        Intent myIntent = new Intent(this, EditAccount.class);
        startActivity(myIntent);
    }

    public void editPassword(View view) {
        Intent myIntent = new Intent(this, EditAccountPassword.class);
        startActivity(myIntent);
    }
}
