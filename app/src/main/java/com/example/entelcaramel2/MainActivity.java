package com.example.entelcaramel2;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.CircularProgressButton;

public class MainActivity extends AppCompatActivity {

    private static final String ENVOLTORIO = "envoltorio";
    private Intent intento;
    private TextView texto;
    private Typeface tipoLetra;
    private AlphaAnimation blinkanimation, fadeIn;
    private ImageView rosa, turquesa, naranja, gris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (TextView)findViewById(R.id.textViewSelecciona);
        rosa = (ImageView)findViewById(R.id.imageViewEnvoltorioRosa);
        turquesa = (ImageView)findViewById(R.id.imageViewEnvoltorioTurquesa);
        naranja = (ImageView)findViewById(R.id.imageViewEnvoltorioNaranja);
        gris = (ImageView)findViewById(R.id.imageViewEnvoltorioGris);

        blinkanimation= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        blinkanimation.setDuration(600); // duration
        blinkanimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        blinkanimation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        blinkanimation.setRepeatMode(Animation.REVERSE);

        fadeIn = new AlphaAnimation(0, 1); // Change alpha from fully visible to invisible
        fadeIn.setDuration(1000); // duration
        fadeIn.setInterpolator(new LinearInterpolator()); // do not alter animation rate

        rosa.setAnimation(fadeIn);
        turquesa.setAnimation(fadeIn);
        naranja.setAnimation(fadeIn);
        gris.setAnimation(fadeIn);

        String rutaFuente = "fuentes/eadesignerregular.ttf";
        this.tipoLetra = Typeface.createFromAsset(getAssets(), rutaFuente);
        texto.setTypeface(tipoLetra);
        texto.setTextSize(20);
        texto.setAnimation(blinkanimation);
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
            if(imagen.getId() == R.id.imageViewEstadisticas){
                intento = new Intent(getApplicationContext(), EstadisticasGlobales.class);
            }

        startActivity(intento);
    }
}
