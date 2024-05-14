package com.example.apiretrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apiretrofit.R;

import org.mindrot.jbcrypt.BCrypt;

public class SignupActvity extends AppCompatActivity {
    private EditText edtName, edtEmail, edtPassword;
    Button btnSign;
    private ProgressBar progressBar;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_actvity);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail1);
        edtPassword = findViewById(R.id.edtPassword1);
        btnSign = findViewById(R.id.Signup);
        progressBar = findViewById(R.id.progressBar);

        tvLogin = findViewById(R.id.Login1);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActvity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                progressBar.setVisibility(View.VISIBLE);
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(SignupActvity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                // Retrieve the stored email from SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                String storedEmail = sharedPreferences.getString("email", "");

                // Check if the entered email matches the stored email
                if (email.equals(storedEmail)) {
                    // Email already exists, inform the user and return
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignupActvity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidEmail(email)) {
                    Toast.makeText(SignupActvity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (!isValidPassword(password)) {
                    Toast.makeText(SignupActvity.this, "Password must be at least 6 characters long and contain at least one special character", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                // Hash the password before saving
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

                // Save the data to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", name);
                editor.putString("email", email);
                editor.putString("password", hashedPassword);
                editor.apply();

                Intent intent = new Intent(SignupActvity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(SignupActvity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean isValidPassword(String password) {
        // Check if password length is at least 6 characters
        // and contains at least one special character
        return password.length() >= 6 && password.matches(".*[!@#$%^&*()_-]+.*");
    }
    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}