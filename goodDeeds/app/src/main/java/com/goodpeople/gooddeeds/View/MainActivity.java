package com.goodpeople.gooddeeds.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;



import com.goodpeople.gooddeeds.Controller.AccountController;
import com.goodpeople.gooddeeds.R;
import com.goodpeople.gooddeeds.View.Account.AccountOptions;
import com.goodpeople.gooddeeds.View.Account.Login;

public class MainActivity extends ViewTemplate {

    AccountController accountController = new AccountController();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.toolbar_login) {
            login();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void editOffer(View view) {
        Intent myIntent = new Intent(this, EditOffer.class);
        startActivity(myIntent);
    }

    public void login() {
        if (!accountController.isLoggedIn()) {
            Intent myIntent = new Intent(this, Login.class);
            startActivity(myIntent);
        } else {
            Intent intent = new Intent(this, AccountOptions.class);
            startActivity(intent);
        }
    }

    public void createOffer(View view) {
        if (accountController.isLoggedIn()) {
            Intent myIntent = new Intent(this, CreateOffer.class);
            startActivity(myIntent);
        } else {
            login();
        }
    }

    public void goToMarket(View view) {
        Intent myIntent = new Intent(this, MarketActivity.class);
        startActivity(myIntent);
    }
}
