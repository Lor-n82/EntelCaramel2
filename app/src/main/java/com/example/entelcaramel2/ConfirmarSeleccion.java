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

import com.example.entelcaramel2.Objetos.Caramelo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfirmarSeleccion extends AppCompatActivity {

    private static final String ENVOLTORIO = "envoltorio";
    private static final String CARAMELO = "caramelo";
    private int envoltorio = 0, caramelo = 0;
    private ImageView imagen, imagenEnvoltorio, imagenCaramelo;
    private AlphaAnimation blinkanimation, fadeIn;
    private TextView texto;
    private Typeface tipoLetra;
    private DatabaseReference caramelosDB;
    private Intent intento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_seleccion);

        imagenEnvoltorio = (ImageView)findViewById(R.id.imageViewEnvoltorioFinal);
        imagenCaramelo = (ImageView)findViewById(R.id.imageViewCarameloFinal);
        imagen = (ImageView)findViewById(R.id.imageViewEnviarDatos);

        texto = (TextView)findViewById(R.id.textViewEnviaDatos);

        //Recojo valores
        envoltorio = getIntent().getExtras().getInt(ENVOLTORIO);
        caramelo = getIntent().getExtras().getInt(CARAMELO);

        switch (envoltorio){
            case 0:imagenEnvoltorio.setImageResource(R.drawable.envoltoriorosa);break;
            case 1:imagenEnvoltorio.setImageResource(R.drawable.envoltoriogris);break;
            case 2:imagenEnvoltorio.setImageResource(R.drawable.envoltorionaranja);break;
            case 3:imagenEnvoltorio.setImageResource(R.drawable.envoltorioturquesa);break;
        }

        switch (caramelo){
            case 0:imagenCaramelo.setImageResource(R.drawable.bolaazul);break;
            case 1:imagenCaramelo.setImageResource(R.drawable.bolagris);break;
            case 2:imagenCaramelo.setImageResource(R.drawable.bolanaranja);break;
            case 3:imagenCaramelo.setImageResource(R.drawable.bolaroja);break;
            case 4:imagenCaramelo.setImageResource(R.drawable.bolaverde);break;
        }

        blinkanimation= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        blinkanimation.setDuration(600); // duration
        blinkanimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        blinkanimation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        blinkanimation.setRepeatMode(Animation.REVERSE);

        fadeIn = new AlphaAnimation(0, 1); // Change alpha from fully visible to invisible
        fadeIn.setDuration(1000); // duration
        fadeIn.setInterpolator(new LinearInterpolator()); // do not alter animation rate

        imagenEnvoltorio.setAnimation(fadeIn);
        imagenCaramelo.setAnimation(fadeIn);

        String rutaFuente = "fuentes/eadesignerregular.ttf";
        this.tipoLetra = Typeface.createFromAsset(getAssets(), rutaFuente);
        texto.setTypeface(tipoLetra);
        texto.setTextSize(20);
        texto.setAnimation(blinkanimation);

        FirebaseApp.initializeApp(this) ;
        caramelosDB = FirebaseDatabase.getInstance().getReference("entelcaramel2");

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intento = new Intent(getApplicationContext(), Estadisticas.class);
                intento.putExtra(ENVOLTORIO,envoltorio);
                intento.putExtra(CARAMELO,caramelo);
                añadirValores(envoltorio, caramelo);
                startActivity(intento);
            }
        });
    }

    public void añadirValores(int envoltorio, int sabor){
        //Enviamos a la DB
        String newid = caramelosDB.push().getKey();
        Caramelo caramelo = new Caramelo(envoltorio,sabor);
        if(newid!=null) {
            caramelosDB.child(newid).setValue(caramelo);
        }
    }
}
