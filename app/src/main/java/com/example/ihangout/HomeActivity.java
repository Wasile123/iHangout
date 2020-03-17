package com.example.ihangout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.ihangout.model.Activiti;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "ViewDatabase";

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference refActivities;
    private DatabaseReference usersRef;
    private String userID;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    List<Activiti> activitiList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbarID);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher_foreground);

        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        usersRef = mFirebaseDatabase.getReference().child("users");
        refActivities = mFirebaseDatabase.getReference().child("activities");
        refActivities.keepSynced(true);
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        activitiList = new ArrayList<>();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in: " + user.getUid());

                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };



        usersRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

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
                        toastMessage(userID);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_profile:
                        toastMessage("Profile selected");
                        drawerLayout.closeDrawers();
                        startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                        return true;

                    case R.id.nav_logout:
                        toastMessage("Logged out!");
                        drawerLayout.closeDrawers();
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(HomeActivity.this, MainActivity.class));
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
                startActivity(new Intent(HomeActivity.this, AddActiviti.class));
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }



    @Override
    public void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<Activiti, ActivitiViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Activiti, ActivitiViewHolder>
                (Activiti.class, R.layout.cardview_layout, ActivitiViewHolder.class, refActivities) {
            @Override
            protected void populateViewHolder(final ActivitiViewHolder holder, final Activiti model, int i) {

                holder.setTitle(model.getTitle());
                holder.setCity(model.getCity());
                holder.setAddress(model.getAddress());
                holder.setDate(model.getDate());
                holder.setName(model.getHostID());
                holder.setPhoneNumber(model.getPhoneNumber());
                holder.setPeople(model.getPlacesLeft());
                Button button = holder.getJoinButton();
                final DatabaseReference thisActivity = refActivities.child(String.valueOf(model.getId()));
                button.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View view) {

                        if (model.getPlacesLeft().equals("0")){
                            toastMessage("No more places!");
                        }
                        else {
                            Integer nrplaces = Integer.valueOf(model.getPlacesLeft());
                            nrplaces--;
                            model.setPlacesLeft(nrplaces.toString());
                            holder.setPeople(model.getPlacesLeft());
                            toastMessage("Place reserved!");

                            Activiti activiti = new Activiti();
                            activiti.setTitle(model.getTitle());
                            activiti.setCity(model.getCity());
                            activiti.setAddress(model.getAddress());
                            activiti.setDate(model.getDate());
                            activiti.setHostID(model.getHostID());
                            activiti.setPhoneNumber(model.getPhoneNumber());
                            activiti.setId(model.getId());
                            activiti.setPlacesLeft(String.valueOf(nrplaces));
                            thisActivity.setValue(activiti);

                        }
                    }
                });
            }
        } ;

        recyclerView.setAdapter(firebaseRecyclerAdapter);

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