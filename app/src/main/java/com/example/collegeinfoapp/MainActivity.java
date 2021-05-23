package com.example.collegeinfoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        setView();
        setToolbar();
    }

    private void setView() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
    }

    private void setToolbar() {
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        setSupportActionBar(toolbar);
        setNavigationView();
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar
                , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //it's instance ties together drawer layout and toolbar
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void setNavigationView() {
        View navigationHeader = navigationView.getHeaderView(0);
        TextView textViewEmail = navigationHeader.findViewById(R.id.text_view_email_profile);
        textViewEmail.setText(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.syllabus :
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main,
                        new SyllabusFragment()).commit();
                break;

            case R.id.attendance :
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main,
                        new AttendanceFragment()).commit();
                break;

            case R.id.logout:
                mAuth.signOut();
                finish();

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}