package com.example.entelcaramel2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PantallaSeleccionCaramelos extends AppCompatActivity {

    private static final String ENVOLTORIO = "envoltorio";
    private static final String CARAMELO = "caramelo";
    private int color = 0;
    private Intent intento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_seleccion_caramelos);
        
        //Recojo el color del envoltorio
        color = getIntent().getExtras().getInt(ENVOLTORIO);

    }
    public void onClick(View vista){

        ImageView imagen = (ImageView) vista;

        intento = new Intent(getApplicationContext(), ConfirmarSeleccion.class);

            if(imagen.getId() == R.id.imageViewBolaAzul){
                intento.putExtra(CARAMELO,0);
            }

            if(imagen.getId() == R.id.imageViewBolaGris){
                intento.putExtra(CARAMELO,1);
            }

            if(imagen.getId() == R.id.imageViewBolaNaranja){
                intento.putExtra(CARAMELO,2);
            }

            if(imagen.getId() == R.id.imageViewBolaRoja){
                intento.putExtra(CARAMELO,3);
            }

            if(imagen.getId() == R.id.imageViewBolaVerde){
                intento.putExtra(CARAMELO,4);
            }

        intento.putExtra(ENVOLTORIO,color);
        startActivity(intento);
    }
}
