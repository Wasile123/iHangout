package com.example.ihangout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ihangout.model.Activiti;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddActiviti extends AppCompatActivity {

    long maxID = 0;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    EditText mTitle, mCity, mStreet, mDate, mNrPlaces;
    Button saveButton;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    DatabaseReference usersRef = ref.child("users");
    DatabaseReference activitiesRef = ref.child("activities");

    String userID;
    FirebaseUser user;
    FirebaseAuth mAuth;

    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactivity);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        toolbar = findViewById(R.id.toolbarID);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Activity");

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher_foreground);


        mTitle = findViewById(R.id.edittext_title_add);
        mCity = findViewById(R.id.edittext_city_add);
        mStreet = findViewById(R.id.edittext_street_add);
        mDate = findViewById(R.id.edittext_date_add);
        mNrPlaces = findViewById(R.id.edittext_nrplaces_add);
        saveButton = findViewById(R.id.button_save_add);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userID = user.getUid();

        DatabaseReference currestUserRef = usersRef.child(userID);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String title = mTitle.getText().toString().trim();
                final String city = mCity.getText().toString().trim();
                final String street = mStreet.getText().toString().trim();
                final String date = mDate.getText().toString().trim();
                final String nrplaces = mNrPlaces.getText().toString().trim();
//                final String phoneNumber = usersRef.child(userID).child("phoneNumber").toString();
                final String hostID = userID;

                if (title.isEmpty()) {
                    mTitle.setError("Please enter an email!");
                    mTitle.requestFocus();
                }
                else if (city.isEmpty()) {
                    mCity.setError("Please enter a username!");
                    mCity.requestFocus();
                }
                else if (street.isEmpty()) {
                    mStreet.setError("Please enter a first name!");
                    mStreet.requestFocus();
                }
                else if (date.isEmpty()) {
                    mDate.setError("Please enter a last name!");
                    mDate.requestFocus();
                }
                else if (nrplaces.isEmpty()) {
                    mNrPlaces.setError("Please enter a phone number!");
                    mNrPlaces.requestFocus();
                }
                else {
                    Activiti activiti = new Activiti();
                    activiti.setTitle(title);
                    activiti.setHostID(hostID);
                    activiti.setCity(city);
                    activiti.setAddress(street);
                    activiti.setPhoneNumber(phoneNumber);
                    activiti.setDate(date);
                    activiti.setPlacesLeft(nrplaces);
                    activiti.setId(maxID + 1);

                    activitiesRef.child(String.valueOf(maxID + 1)).setValue(activiti);
                }

            }
        });




        activitiesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxID = dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        currestUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                phoneNumber = dataSnapshot.child("phoneNumber").getValue().toString();
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
                        startActivity(new Intent(AddActiviti.this, HomeActivity.class));
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_profile:
                        toastMessage("Profile selected");
                        drawerLayout.closeDrawers();
                        startActivity(new Intent(AddActiviti.this, ProfileActivity.class));
                        return true;

                    case R.id.nav_logout:
                        toastMessage("Logged out!");
                        drawerLayout.closeDrawers();
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(AddActiviti.this, MainActivity.class));
                        return true;

                }
                return false;
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {

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
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_addActiviti:
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }


    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}