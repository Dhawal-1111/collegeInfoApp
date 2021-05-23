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

public class LoginFragment extends Fragment {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewSignup;

    private String email;
    private String password;

    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mAuth = FirebaseAuth.getInstance();

        setView(view);
        setClickListeners();
        return view;
    }

    private void setView(View view) {
        editTextEmail = view.findViewById(R.id.editTextEmailAddressLogin);
        editTextPassword = view.findViewById(R.id.editTextPasswordLogin);
        buttonLogin = view.findViewById(R.id.buttonLogin);
        textViewSignup = view.findViewById(R.id.textViewSignup);
    }

    private void setClickListeners() {
        buttonLogin.setOnClickListener(view -> {
            email = editTextEmail.getText().toString().trim();
            password = editTextPassword.getText().toString().trim();
            login();
        });

        textViewSignup.setOnClickListener(view -> {
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().replace(R.id.frame_layout_signup_login,
                    new SignupFragment()).commit();
        });
    }

    private void login() {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}