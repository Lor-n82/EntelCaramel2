package com.example.entelcaramel2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmarSeleccion extends AppCompatActivity {

    private static final String ENVOLTORIO = "envoltorio";
    private static final String CARAMELO = "caramelo";
    private int envoltorio = 0, caramelo = 0;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_seleccion);

        //Recojo valores
        envoltorio = getIntent().getExtras().getInt(ENVOLTORIO);
        caramelo = getIntent().getExtras().getInt(CARAMELO);

    }

    public void onClick(View vista){
        imagen = (ImageView) vista;

        if(imagen.getId() == R.id.imageViewEnviarDatos){

        }
    }
}
