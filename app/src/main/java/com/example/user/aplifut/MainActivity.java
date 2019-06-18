package com.example.user.aplifut;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.user.aplifut.Fragments.EquiposFragment;
import com.example.user.aplifut.Fragments.MarcadoresFragment;
import com.example.user.aplifut.Fragments.PartidosFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Hola";
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
                    case R.id.partidosItem:
                        PartidosFragment partidosFragment = new PartidosFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,partidosFragment)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.equiposItem:
                        EquiposFragment equiposFragment = new EquiposFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,equiposFragment)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.marcadoresItem:
                        MarcadoresFragment marcadoresFragment = new MarcadoresFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,marcadoresFragment)
                                .addToBackStack(null).commit();
                        break;
                }
                return true;
            }
        });
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQR();
            }
        });
    }

    private void addQR() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (resultCode == RESULT_OK) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(
                    requestCode, resultCode, intent);
            if (scanResult != null) {
                String contents = scanResult.getContents();

                String [] valores= contents.split("@");

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.modal_futbolista, null);
                final TextView nombreFut = (TextView) mView.findViewById(R.id.nombreJugador);
                final TextView ApellidoFut = (TextView) mView.findViewById(R.id.apellidoJugador);
                final TextView equipoFut = (TextView) mView.findViewById(R.id.equipoJugador);
                final TextView golesFut = (TextView) mView.findViewById(R.id.golesJugador);
                final ImageView FotoFut = (ImageView) mView.findViewById(R.id.fotoFutbolista);

                nombreFut.setText(valores[1]);
                ApellidoFut.setText(valores[2]);
                equipoFut.setText(valores[3]);
                golesFut.setText(valores[4]);
                Glide.with(this)
                        .load(valores[0])
                        .centerCrop()
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(FotoFut);


                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                Log.i(TAG, "MyClass.getView() — get item number ");
            }
        } else if (resultCode == RESULT_CANCELED) {
            Log.i(TAG, "MyClass.getView() — get item number ");
            Toast.makeText(this, R.string.scan_canceled, Toast.LENGTH_SHORT)
                    .show();
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomBar);
        bottomNavigationView.setSelectedItemId(R.id.partidosItem);
    }


}
