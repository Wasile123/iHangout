package com.example.ihangout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihangout.model.UserInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    EditText mEmail, mHometown, mFirstName, mLastName, mPhoneNumber, mPassword;
    Button mRegisterButton;
    TextView mTextViewLogin;
    FirebaseAuth mAuth;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    DatabaseReference myRef = ref.child("users");
    UserInformation userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = findViewById(R.id.edittext_email);
        mHometown = findViewById(R.id.edittext_hometown);
        mFirstName = findViewById(R.id.edittext_firstname);
        mLastName = findViewById(R.id.edittext_lastname);
        mPhoneNumber = findViewById(R.id.edittext_phonenumber);
        mPassword = findViewById(R.id.edittext_password);
        mRegisterButton = findViewById(R.id.button_register);
        mTextViewLogin = findViewById(R.id.textview_register);

        mAuth = FirebaseAuth.getInstance();


        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = mEmail.getText().toString().trim();
                final String hometown = mHometown.getText().toString().trim();
                final String firstName = mFirstName.getText().toString().trim();
                final String lastName = mLastName.getText().toString().trim();
                final String phoneNumber = mPhoneNumber.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();


                if (email.isEmpty()) {
                    mEmail.setError("Please enter an email!");
                    mEmail.requestFocus();
                }
                else if (hometown.isEmpty()) {
                    mHometown.setError("Please enter a username!");
                    mHometown.requestFocus();
                }
                else if (firstName.isEmpty()) {
                    mFirstName.setError("Please enter a first name!");
                    mFirstName.requestFocus();
                }
                else if (lastName.isEmpty()) {
                    mLastName.setError("Please enter a last name!");
                    mLastName.requestFocus();
                }
                else if (phoneNumber.isEmpty()) {
                    mPhoneNumber.setError("Please enter a phone number!");
                    mPhoneNumber.requestFocus();
                }
                else if (password.isEmpty()) {
                    mPassword.setError("Please enter password!");
                    mPassword.requestFocus();
                }
                else if (email.isEmpty() && password.isEmpty()){
                    toastMessage("You have to fill all the fields!");
                }
                else if (!email.equals("") && !password.equals("")) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                toastMessage("Sign up unsuccessful! Try again!");
                            }
                            else {
                                userID = new UserInformation();
                                userID.setHometown(hometown);
                                userID.setEmail(email);
                                userID.setFirstName(firstName);
                                userID.setLastName(lastName);
                                userID.setPhoneNumber(phoneNumber);

                                myRef.child(mAuth.getUid()).setValue(userID);

                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            }
                        }
                    });
                }
                else {
                    toastMessage("An error occured");
                }

            }
        });

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        mEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mLastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mPhoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mHometown.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }



    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
