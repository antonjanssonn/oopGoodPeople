package com.goodpeople.gooddeeds.View.Account;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.goodpeople.gooddeeds.R;
import com.goodpeople.gooddeeds.View.ViewTemplate;

import java.util.Locale;

public class EditAccount extends ViewTemplate {

    TextView nameView;
    TextView emailView;
    TextView postalCodeView;

    String name;
    String email;
    int postalCode;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.edit_account);
        super.onCreate(savedInstanceState);

        accountController.accountHandler();
        getTextViews();
        populateFields();
    }

    private void getTextViews() {
        this.nameView = (EditText) findViewById(R.id.account_edit_name);
        this.emailView = (EditText) findViewById(R.id.account_edit_email);
        this.postalCodeView = (EditText) findViewById(R.id.account_edit_postal_code);
    }

    public void populateFields() {
        nameView.setText(accountController.accountHandler().getName());
        emailView.setText(accountController.accountHandler().getEmail());
        postalCodeView.setText(String.format(Locale.getDefault(), "%d", accountController.accountHandler().getPostalCode()));
    }


    public void saveUpdates(View view) {
        getFilledOutFields();

        if (validateEditedFields()) {
            accountController.editAccountHandler(name, email, postalCode);
            finish();
        } else {
            setError();
        }
    }

    private void getFilledOutFields() {
        EditText editTextName = findViewById(R.id.account_edit_name);
        name = editTextName.getText().toString();

        EditText editTextEmail = findViewById(R.id.account_edit_email);
        email = editTextEmail.getText().toString();

        EditText editTextPostalCode = findViewById(R.id.account_edit_postal_code);
        if (!editTextPostalCode.getText().toString().trim().isEmpty()) {
            postalCode = Integer.valueOf(editTextPostalCode.getText().toString().trim());
        } else {
            postalCode = 0;
        }
    }

    private boolean validateEditedFields() {
        if (isEmailChanged()) {
            return (!accountController.isEmailUsedHandler(email) && !name.trim().isEmpty() && isEmailValid(email) &&
                    isPostalCodeValid(postalCode));
        } else {
            return (!name.trim().isEmpty() &&
                    isPostalCodeValid(postalCode));
        }
    }

    private boolean isEmailChanged() {
        return !this.email.equals(accountController.accountHandler().getEmail());
    }


    private void setError() {
        if (name.trim().isEmpty()) {
            sendError(R.id.account_edit_name_layout, R.string.invalid_name);
        } else {
            removeError(R.id.account_edit_name_layout);
        }

        if (!isEmailValid(email)) {
            sendError(R.id.account_edit_email_layout, R.string.invalid_email);

        } else {
            removeError(R.id.account_edit_email_layout);
        }
        if (!isPostalCodeValid(postalCode)) {
            sendError(R.id.account_edit_postal_code_layout, R.string.invalid_postal_code);

        } else {
            removeError(R.id.account_edit_postal_code_layout);
        }
    }
}



