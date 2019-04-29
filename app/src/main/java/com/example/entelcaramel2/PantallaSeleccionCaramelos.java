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


public class PantallaSeleccionCaramelos extends AppCompatActivity {

    private static final String ENVOLTORIO = "envoltorio";
    private static final String CARAMELO = "caramelo";
    private int color = 0;
    private Intent intento;
    private AlphaAnimation blinkanimation, fadeIn;
    private ImageView azul, gris, naranja , rojo, verde;
    private TextView texto;
    private Typeface tipoLetra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_seleccion_caramelos);

        //Recojo el color del envoltorio
        color = getIntent().getExtras().getInt(ENVOLTORIO);

        texto = (TextView)findViewById(R.id.textViewSeleccionCaramelo);
        azul = (ImageView)findViewById(R.id.imageViewBolaAzul);
        gris = (ImageView)findViewById(R.id.imageViewBolaGris);
        naranja = (ImageView)findViewById(R.id.imageViewBolaNaranja);
        rojo = (ImageView)findViewById(R.id.imageViewBolaRoja);
        verde = (ImageView)findViewById(R.id.imageViewBolaVerde);

        blinkanimation= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        blinkanimation.setDuration(600); // duration
        blinkanimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        blinkanimation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        blinkanimation.setRepeatMode(Animation.REVERSE);

        fadeIn = new AlphaAnimation(0, 1); // Change alpha from fully visible to invisible
        fadeIn.setDuration(1000); // duration
        fadeIn.setInterpolator(new LinearInterpolator()); // do not alter animation rate

        azul.setAnimation(fadeIn);
        gris.setAnimation(fadeIn);
        naranja.setAnimation(fadeIn);
        rojo.setAnimation(fadeIn);
        verde.setAnimation(fadeIn);

        String rutaFuente = "fuentes/eadesignerregular.ttf";
        this.tipoLetra = Typeface.createFromAsset(getAssets(), rutaFuente);
        texto.setTypeface(tipoLetra);
        texto.setTextSize(20);
        texto.setAnimation(blinkanimation);

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
