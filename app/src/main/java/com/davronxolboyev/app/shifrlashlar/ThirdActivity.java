package com.davronxolboyev.app.shifrlashlar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.davronxolboyev.app.shifrlashlar.fragments.AffianFr;
import com.davronxolboyev.app.shifrlashlar.fragments.ElGamanFr;
import com.davronxolboyev.app.shifrlashlar.fragments.GamiltonFr;
import com.davronxolboyev.app.shifrlashlar.fragments.PolibiyaFr;
import com.davronxolboyev.app.shifrlashlar.fragments.RsaFr;
import com.davronxolboyev.app.shifrlashlar.fragments.SezarFr;
import com.davronxolboyev.app.shifrlashlar.fragments.VijinerFr;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;


public class ThirdActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    int id;
    String usul;
    TextView headerTitle;
    NavigationView navigationView;
    View headerView;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Qiymatlarni qabul qilish
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("ID");
        usul = bundle.getString("Usul");

        // Headerdagi so'zni o`zgartirish
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ChangeHeader(usul);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        titleToolbar();
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Fragmentni o`zgarishi
        FindId(id);
        if (navigationView.isClickable()) {
            Toast.makeText(getApplicationContext(), "Tekshirildi!", Toast.LENGTH_LONG).show();
        }
    }

    private void titleToolbar() {
        if (MainActivity.ID == 1) {
            toolbar.setTitle("Shifrlash jarayoni");
        } else {
            toolbar.setTitle("Deshifrlash jarayoni");
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void ChangeHeader(String text) {
        headerView = navigationView.getHeaderView(0);
        headerTitle = headerView.findViewById(R.id.header_title);
        headerTitle.setText(text);
    }

    private void FindId(int id) {
        Fragment fragment = null;
        switch (id) {
            case 1:
                fragment = new SezarFr();
                break;
            case 2:
                fragment = new VijinerFr();
                break;
            case 3:
                fragment = new RsaFr();
                break;
            case 4:
                fragment = new PolibiyaFr();
                break;
            case 5:
                fragment = new AffianFr();
                break;
            case 6:
                fragment = new ElGamanFr();
                break;
            case 7:
                fragment = new GamiltonFr();
                break;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        assert fragment != null;
        ft.replace(R.id.fragment, fragment);
        ft.commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.sezarM: {
                FindId(1);
                usul = item.getTitle().toString();
                ChangeHeader(usul);
                onBackPressed();
                return true;
            }
            case R.id.vijinerM: {
                onBackPressed();
                FindId(2);
                usul = item.getTitle().toString();
                ChangeHeader(usul);
                return true;
            }
            case R.id.rsaM: {
                FindId(3);
                usul = item.getTitle().toString();
                ChangeHeader(usul);
                onBackPressed();
                return true;
            }
            case R.id.polibiyaM: {
                FindId(4);
                usul = item.getTitle().toString();
                ChangeHeader(usul);
                onBackPressed();
                return true;
            }
            case R.id.affianM: {
                FindId(5);
                usul = item.getTitle().toString();
                ChangeHeader(usul);
                onBackPressed();
                return true;
            }
            case R.id.gamiltonM: {
                FindId(7);
                usul = item.getTitle().toString();
                ChangeHeader(usul);
                onBackPressed();
                return true;
            }
            case R.id.elgamanM: {
                FindId(6);
                usul = item.getTitle().toString();
                ChangeHeader(usul);
                onBackPressed();
                return true;
            }
            case R.id.programmers: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Dasturchilar")
                        .setIcon(R.drawable.ic_baseline_code_24)
                        .setMessage("Backend dasturchi : Allaberganov Abror\nAndroid dasturchi : Xolboyev Davron\n\nTATU \"Kompyuter Injenering Fakulteti\" 211-20 guruh")
                        .setNeutralButton("OK", (dialog, which) -> dialog.cancel());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                onBackPressed();
                return true;
            }
            case R.id.allApps: {
                Uri uri = Uri.parse("https://t.me/davron_xolboyev_apps");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            }
        }

        ChangeHeader(usul);
        Log.d("USUL : ", usul);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shifrlashM:
                MainActivity.ID = 1;
                titleToolbar();
                return true;
            case R.id.deshifrlashM:
                MainActivity.ID = 2;
                titleToolbar();
                return true;
            case R.id.exitM:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Shifrlashlar")
                        .setIcon(R.drawable.ic_baseline_code_24)
                        .setMessage("Dasturdan chiqasizmi?")
                        .setNegativeButton("Ha", (dialog, which) -> finishAffinity())
                        .setPositiveButton("Yo`q", ((dialog, which) -> dialog.cancel()));
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;

            case R.id.infoM:
                AlertDialog.Builder info = new AlertDialog.Builder(this);
                info.setTitle("Ma`lumot")
                        .setIcon(R.drawable.ic_baseline_info_24)
                        .setMessage("Internetdan " + usul + " deb izla! :)")
                        .setNeutralButton("OK", (dialog, which) -> dialog.cancel());
                AlertDialog infoDialog = info.create();
                infoDialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}