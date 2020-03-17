package com.example.ihangout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ihangout.model.UserInformation;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ViewDatabase";

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private DatabaseReference ref;
    private DatabaseReference userReference;
    private String userID;

    private UserInformation user;

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private EditText mEmail, mFirstName, mLastName, mPhoneNumber, mHomeTown;
    private Button mSaveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        ref = mFirebaseDatabase.getReference().child("users");
        FirebaseUser user = mAuth.getCurrentUser();
        Log.d(TAG, "mauth Getcurrentuser = " + user);
        userID = user.getUid();
        Log.d(TAG, "userID = " + userID);
        myRef = ref.child(userID);



        toolbar = findViewById(R.id.toolbarID);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher_foreground);


        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        mEmail = findViewById(R.id.edittext_email);
        mFirstName = findViewById(R.id.edittext_firstname);
        mLastName = findViewById(R.id.edittext_lastname);
        mPhoneNumber = findViewById(R.id.edittext_phonenumber);
        mHomeTown = findViewById(R.id.edittext_hometown);

        mSaveButton = findViewById(R.id.button_save);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in: " + user.getUid());
                    Log.d(TAG, "REFERENCE: " + myRef.toString());

                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_activity:
                        toastMessage("Activity selected");
                        startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_profile:
//                        toastMessage("Profile selected");
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_logout:
                        toastMessage("Logged out!");
                        drawerLayout.closeDrawers();
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                        return true;

                }

                return false;
            }
        });


        mSaveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String email = mEmail.getText().toString();
                String firstname = mFirstName.getText().toString();
                String lastname = mLastName.getText().toString();
                String phoneNr = mPhoneNumber.getText().toString();
                String city = mHomeTown.getText().toString();

                editData(email, firstname, lastname, phoneNr, city);

                toastMessage("Profile Updated!");
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


        mHomeTown.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private void editData(String email, String firstname, String lastname, String phoneNr, String city) {

        user = new UserInformation();
        user.setEmail(email);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setPhoneNumber(phoneNr);
        user.setHometown(city);

        myRef.setValue(user);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {

                toastMessage("Action View Expanded");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {

                toastMessage("Action View Collapsed");
                return true;
            }
        };

        MenuItem searchItem = menu.findItem(R.id.action_search);

        searchItem.setOnActionExpandListener(onActionExpandListener);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.action_settings:
                toastMessage("Settings");
                return true;
            case R.id.action_addActiviti:
                startActivity(new Intent(ProfileActivity.this, AddActiviti.class));
                return true;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }


    public void showData(DataSnapshot dataSnapshot) {

        mEmail.setText(dataSnapshot.child("email").getValue().toString());
        mFirstName.setText(dataSnapshot.child("firstName").getValue().toString());
        mLastName.setText(dataSnapshot.child("lastName").getValue().toString());
        mPhoneNumber.setText(dataSnapshot.child("phoneNumber").getValue().toString());
        mHomeTown.setText(dataSnapshot.child("hometown").getValue().toString());
    }



    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }



    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}