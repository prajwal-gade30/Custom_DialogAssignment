package com.example.custom_dialog_assignment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddContactDialog extends DialogFragment {

    public interface OnContactAddedListener {
        void onContactAdded(String name, String phone);
    }

    public  OnContactAddedListener contactListener;
    public EditText editTextName;
    public EditText editTextPhone;
    public Button buttonSave;
    public Button buttonCancel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            contactListener = (OnContactAddedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnContactAddedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_dialog, container, false);


        editTextName = view.findViewById(R.id.editTextName);
        editTextPhone = view.findViewById(R.id.editTextPhone);
        buttonSave = view.findViewById(R.id.buttonSave);
        buttonCancel = view.findViewById(R.id.buttonCancel);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = editTextName.getText().toString().trim();
                String inputPhone = editTextPhone.getText().toString().trim();


                if (TextUtils.isEmpty(inputName) || TextUtils.isEmpty(inputPhone)) {
                    Toast.makeText(getActivity(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (inputPhone.length() < 10) {
                    Toast.makeText(getActivity(), "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                    return;
                }


                contactListener.onContactAdded(inputName, inputPhone);
                dismiss();
            }
        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // Close dialog without saving
            }
        });

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        return dialog;
    }
}