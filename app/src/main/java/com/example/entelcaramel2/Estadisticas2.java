package com.example.entelcaramel2;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Estadisticas2 extends AppCompatActivity {

    private static final String ENVOLTORIO = "envoltorio";
    private static final String CARAMELO = "caramelo";
    private int envoltorio = 0, sabor = 0;
    private ArrayList<Integer> envoltorios = new ArrayList<>();
    private ArrayList<Integer> sabores = new ArrayList<>();
    private ArrayList<Integer> estadisticas = new ArrayList<>();
    int cont = 0, mismoEnvoltorio = 0;
    private DatabaseReference fireDB;
    private List<SliceValue> pieData = new ArrayList<>();
    private PieChartView pieChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas2);

        pieChartView = findViewById(R.id.chart);

        envoltorio = getIntent().getExtras().getInt(ENVOLTORIO);
        sabor = getIntent().getExtras().getInt(CARAMELO);

        fireDB = FirebaseDatabase.getInstance().getReference().child("entelcaramel2");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot nodo : dataSnapshot.getChildren()){
                    envoltorios.add(Integer.parseInt(nodo.child("envoltorio").getValue().toString()));
                    sabores.add(Integer.parseInt(nodo.child("sabor").getValue().toString()));

                    if(envoltorios.get(cont) == envoltorio){
                        estadisticas.add(sabores.get(cont));
                        mismoEnvoltorio +=1;
                    }

                    cont += 1;
                }
                createData(envoltorio);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        fireDB.addListenerForSingleValueEvent(eventListener);

    }

    private void createData(int envoltorio){
        PieChartData pieChartData;
        int azul=0, gris=0, naranja=0, rojo=0, verde=0, defecto=0;
        for (int posicion : estadisticas){
            /*switch (posicion){
                case 0:pieData.add(new SliceValue(posicion, Color.BLUE));break;
                case 1:pieData.add(new SliceValue(posicion, Color.GRAY));break;
                case 2:pieData.add(new SliceValue(posicion, Color.rgb(200,100,10)));break;
                case 3:pieData.add(new SliceValue(posicion, Color.RED));break;
                case 4:pieData.add(new SliceValue(posicion, Color.GREEN));break;
                default:pieData.add(new SliceValue(posicion, Color.BLACK));break;
            }*/
            switch (posicion){
                case 0:azul++;break;
                case 1:gris++;break;
                case 2:naranja++;break;
                case 3:rojo++;break;
                case 4:verde++;break;
                default:defecto++;break;
            }
        }
        if(azul !=0) {
            pieData.add(new SliceValue(azul, Color.BLUE));
        }
        if(gris !=0) {
            pieData.add(new SliceValue(gris, Color.GRAY));
        }
        if(naranja !=0) {
            pieData.add(new SliceValue(naranja, Color.rgb(220, 100, 10)));
        }
        if(rojo !=0) {
            pieData.add(new SliceValue(rojo, Color.RED));
        }
        if(verde !=0) {
            pieData.add(new SliceValue(verde, Color.GREEN));
        }
        if(defecto !=0) {
            pieData.add(new SliceValue(defecto, Color.BLACK));
        }

        pieChartData = new PieChartData(pieData);

        String env="";
        switch (envoltorio){
            case 0:env=getResources().getString(R.string.carameloRosa);break;
            case 1:env=getResources().getString(R.string.carameloGris);break;
            case 2:env=getResources().getString(R.string.caramelonaranja);break;
            case 3:env=getResources().getString(R.string.carameloTurquesa);break;
        }
        pieChartData.setHasLabels(true);
        pieChartData.setHasCenterCircle(true).setCenterText1(env + " " + mismoEnvoltorio)
                .setCenterText1FontSize(16).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
