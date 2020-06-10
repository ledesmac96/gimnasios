package com.example.gimnasio.Activity;

import android.content.pm.ActivityInfo;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gimnasio.Fragment.MainFragment;
import com.example.gimnasio.R;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar mToolbar;
    View headerView;
    Fragment mFragment;
    int itemSelecionado = -1, idUser = 0;
    ImageView imgPerfil, imgBienestar;
    TextView txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        loadViews();

        comprobarNavigationView();

        setToolbar();

        loadData();


    }


    private void loadData() {
    }

    private void loadViews() {
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.toolbar);
        navigationView.removeHeaderView(navigationView.getHeaderView(0));
        headerView = navigationView.inflateHeaderView(R.layout.cabecera_drawer);
        imgBienestar = headerView.findViewById(R.id.logoBienestar);
        txtNombre = headerView.findViewById(R.id.txtNombreUser);
    }

    private void comprobarNavigationView() {
        if (navigationView != null) {
            prepararDrawer(navigationView);
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
        navigationView.setCheckedItem(R.id.item_inicio);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }



    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            case R.id.item_inicio:
                fragmentoGenerico = new MainFragment();
                break;

        }

        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        mFragment = fragmentoGenerico;

        itemSelecionado = itemDrawer.getItemId();

        if (itemDrawer.getItemId() != R.id.item_config) {
            ((TextView) findViewById(R.id.txtNombreUser)).setText(itemDrawer.getTitle());
        }
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle("Bienestar Estudiantíl");
            mToolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(Gravity.LEFT);
        else if (!(mFragment instanceof MainFragment)) {
            seleccionarItem(navigationView.getMenu().getItem(0));
            navigationView.setCheckedItem(R.id.item_inicio);
        } else
            super.onBackPressed();
    }

}
