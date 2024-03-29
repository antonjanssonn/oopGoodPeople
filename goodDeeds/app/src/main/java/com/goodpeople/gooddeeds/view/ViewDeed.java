package com.goodpeople.gooddeeds.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.goodpeople.gooddeeds.model.entities.IDeed;
import com.goodpeople.gooddeeds.R;

/**
 * Responsible for providing information about a deed.
 */

public class ViewDeed extends ViewTemplate {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.view_deed);
        super.onCreate(savedInstanceState);
        loadDeed();
        View button = findViewById(R.id.edit_offer);
        Button deleteButton = findViewById(R.id.deleteButton);
        if (!deedController.isMyActiveDeedHandler()) {
            button.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }
    }

    private void loadDeed() {
        IDeed deed = deedController.getCurrentDeedHandler();
        TextView subject = findViewById(R.id.deedSubject);
        subject.setText(deed.getSubject());
        TextView description = findViewById(R.id.deedDescription);
        description.setText(deed.getDescription());
        View button = findViewById(R.id.edit_offer);
        if (!deedController.isMyActiveDeedHandler()) {
            button.setVisibility(View.GONE);
        }
        View claimButton = findViewById(R.id.claim_deed);
        if (deedController.isMyOwnDeedHandler() &&
                (!deedController.isClaimedHandler())) {
            claimButton.setVisibility(View.GONE);
        }
        claimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deedController.claimDeedHandler();
                finish();
            }
        });
    }

    public void editDeed(View view) {
        Intent myIntent = new Intent(this, EditDeed.class);
        startActivity(myIntent);
    }

    public void onDeleteClick(View v) {
        if (!deedController.isClaimedHandler()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Delete");
            alert.setMessage("Are you sure you want to delete?");
            alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    deedController.deleteCurrentDeedHandler();
                    finish();
                }
            });
            alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alert.show();
        } else {
            if (deedController.showMyActiveRequestsHandler().isEmpty()) {
                Toast toast = Toast.makeText(this, "You cannot delete a Deed that is claimed.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDeed();
    }
}
