package com.example.user.aplifut;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;

import com.example.user.aplifut.Fragments.EquiposFragment;
import com.example.user.aplifut.Fragments.MarcadoresFragment;
import com.example.user.aplifut.Fragments.PartidosFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomBar);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.equiposItem:
                        EquiposFragment equiposFragment = new EquiposFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner,equiposFragment)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.partidosItem:
                        PartidosFragment partidosFragment = new PartidosFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner,partidosFragment)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.marcadoresItem:
                        MarcadoresFragment marcadoresFragment = new MarcadoresFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner,marcadoresFragment)
                                .addToBackStack(null).commit();
                        break;
                }
                return true;
            }
        });


    }
}
