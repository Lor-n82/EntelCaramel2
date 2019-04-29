package com.example.entelcaramel2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String ENVOLTORIO = "envoltorio";
    private Intent intento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View vista){

        ImageView imagen = (ImageView) vista;

        intento = new Intent(getApplicationContext(), PantallaSeleccionCaramelos.class);

            if(imagen.getId() == R.id.imageViewEnvoltorioRosa){
                intento.putExtra(ENVOLTORIO,0);
            }

            if(imagen.getId() == R.id.imageViewEnvoltorioGris){
                intento.putExtra(ENVOLTORIO,1);
            }

            if(imagen.getId() == R.id.imageViewEnvoltorioNaranja){
                intento.putExtra(ENVOLTORIO,2);
            }

            if(imagen.getId() == R.id.imageViewEnvoltorioTurquesa){
                intento.putExtra(ENVOLTORIO,3);
            }

        startActivity(intento);
    }
}
