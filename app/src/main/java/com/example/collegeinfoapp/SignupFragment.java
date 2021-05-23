package com.example.collegeinfoapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class SignupFragment extends Fragment {

    private EditText editTextEmail;
    private EditText editTextCreatePassword;
    private EditText editTextConfirmPassword;
    private Button buttonSignup;
    private TextView textViewLogin;

    private String email;
    private String createPassword;
    private String confirmPassword;

    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        mAuth = FirebaseAuth.getInstance();

        setView(view);
        setClickListeners();
        return view;
    }

    private void setView(View view) {
        editTextEmail = view.findViewById(R.id.editTextTextEmailAddress);
        editTextCreatePassword = view.findViewById(R.id.editTextCreatePassword);
        editTextConfirmPassword = view.findViewById(R.id.editTextTextConfirmPassword);
        buttonSignup = view.findViewById(R.id.buttonSignup);
        textViewLogin = view.findViewById(R.id.textViewLogin);
    }

    private void setClickListeners() {
        buttonSignup.setOnClickListener(view -> {
            email = editTextEmail.getText().toString();
            createPassword = editTextCreatePassword.getText().toString().trim();
            confirmPassword = editTextConfirmPassword.getText().toString().trim();

            if(createPassword.equals(confirmPassword)) {
                addUser();
            }
            else {
                Toast.makeText(getContext(), "Passwords do not match!",
                        Toast.LENGTH_LONG).show();
            }
        });

        textViewLogin.setOnClickListener(view -> {
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().replace(R.id.frame_layout_signup_login,
                    new LoginFragment()).commit();
        });
    }

    private void addUser() {
        mAuth.createUserWithEmailAndPassword(email, createPassword)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getContext(),
                                "Sign up unsuccessful!", Toast.LENGTH_LONG).show();
                    }
                });
    }
}