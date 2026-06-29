package com.example.custom_dialog_assignment;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AddContactDialog.OnContactAddedListener {

    public Button buttonAddContact;
    public TextView textViewContactDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAddContact = findViewById(R.id.buttonAddContact);
        textViewContactDisplay =findViewById(R.id.textViewContactDisplay);


        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddContactDialog dialog = new AddContactDialog();
                dialog.show(getSupportFragmentManager(), "AddContactDialog");
            }
        });
    }

    @Override
    public void onContactAdded(String name, String phone) {
        String formattedOutput = "Latest Contact Details Added:\n\n" +
                "Name: " + name + "\n" +
                "Phone: " + phone;


        textViewContactDisplay.setText(formattedOutput);
    }
}