package com.example.entelcaramel2;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

public class EstadisticasGlobales extends AppCompatActivity {

    private DatabaseReference fireDB;
    private List<SliceValue> pieDataRosa = new ArrayList<>();
    private List<SliceValue> pieDataTurquesa = new ArrayList<>();
    private List<SliceValue> pieDataNaranja = new ArrayList<>();
    private List<SliceValue> pieDataGris = new ArrayList<>();
    private PieChartView pieChartViewRosa, pieChartViewTurquesa, pieChartViewNaranja, pieChartViewGris ;
    private ArrayList<Integer> envoltorios = new ArrayList<>();
    private ArrayList<Integer> sabores = new ArrayList<>();
    private ArrayList<Integer> estadisticasRosa = new ArrayList<>();
    private ArrayList<Integer> estadisticasTurquesa = new ArrayList<>();
    private ArrayList<Integer> estadisticasNaranja = new ArrayList<>();
    private ArrayList<Integer> estadisticasGris = new ArrayList<>();
    private int cont = 0, contR = 0, contT = 0, contN = 0, contG = 0;
    private Typeface tipoLetra;
    private TextView rosa, turquesa, naranja, gris, totalRosa, totalTurquesa, totalNaranja, totalGris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_globales);

        pieChartViewRosa = (PieChartView)findViewById(R.id.chartRosa);
        pieChartViewTurquesa = (PieChartView)findViewById(R.id.chartTurquesa);
        pieChartViewNaranja = (PieChartView)findViewById(R.id.chartNaranja);
        pieChartViewGris = (PieChartView)findViewById(R.id.chartGris);

        rosa = (TextView)findViewById(R.id.textViewRosa);
        turquesa = (TextView)findViewById(R.id.textViewTotalTurquesa);
        naranja = (TextView)findViewById(R.id.textViewNaranja);
        gris = (TextView)findViewById(R.id.textViewTotalGris);
        totalRosa = (TextView)findViewById(R.id.textViewTotalRosa);
        totalTurquesa = (TextView)findViewById(R.id.textViewTotalTurquesa);
        totalNaranja = (TextView)findViewById(R.id.textViewTotalNaranja);
        totalGris = (TextView)findViewById(R.id.textViewTotalGris);

        String rutaFuente = "fuentes/eadesignerregular.ttf";
        this.tipoLetra = Typeface.createFromAsset(getAssets(), rutaFuente);
        rosa.setTypeface(tipoLetra);
        turquesa.setTypeface(tipoLetra);
        naranja.setTypeface(tipoLetra);
        gris.setTypeface(tipoLetra);
        totalRosa.setTypeface(tipoLetra);
        totalTurquesa.setTypeface(tipoLetra);
        totalNaranja.setTypeface(tipoLetra);
        totalGris.setTypeface(tipoLetra);

        fireDB = FirebaseDatabase.getInstance().getReference().child("entelcaramel2");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot nodo : dataSnapshot.getChildren()){
                    envoltorios.add(Integer.parseInt(nodo.child("envoltorio").getValue().toString()));
                    sabores.add(Integer.parseInt(nodo.child("sabor").getValue().toString()));

                    if(envoltorios.get(cont) == 0){
                        estadisticasRosa.add(sabores.get(cont));
                    }
                    if(envoltorios.get(cont) == 1){
                        estadisticasGris.add(sabores.get(cont));
                    }
                    if(envoltorios.get(cont) == 2){
                        estadisticasNaranja.add(sabores.get(cont));
                    }
                    if(envoltorios.get(cont) == 3){
                        estadisticasTurquesa.add(sabores.get(cont));
                    }

                    cont += 1;
                }

                if(estadisticasRosa.size() > 1) {
                    totalRosa.setText(estadisticasRosa.size() + " caramelos");
                }else{
                    totalRosa.setText(estadisticasRosa.size() + " caramelo");
                }

                if(estadisticasTurquesa.size() > 1 ) {
                    totalTurquesa.setText(estadisticasTurquesa.size() + " caramelos");
                }else{
                    totalTurquesa.setText(estadisticasTurquesa.size() + " caramelo");
                }

                if(estadisticasNaranja.size() > 1){
                    totalNaranja.setText(estadisticasNaranja.size() + " caramelos");
                }else{
                    totalNaranja.setText(estadisticasNaranja.size() + " caramelo");
                }

                if(estadisticasGris.size() > 1){
                    totalGris.setText(estadisticasGris.size() + " caramelos");
                }else{
                    totalGris.setText(estadisticasGris.size() + " caramelo");
                }


                createData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        fireDB.addListenerForSingleValueEvent(eventListener);

    }

    private void createData(){
        PieChartData pieChartDataRosa, pieChartDataTurquesa, pieChartDataNaranja, pieChartDataGris;
        int azul=0, gris=0, naranja=0, rojo=0, verde=0, defecto=0;

        if(estadisticasRosa.size() > 0) {

            for (int posicion : estadisticasRosa) {

                switch (posicion) {
                    case 0:
                        azul++;
                        break;
                    case 1:
                        gris++;
                        break;
                    case 2:
                        naranja++;
                        break;
                    case 3:
                        rojo++;
                        break;
                    case 4:
                        verde++;
                        break;
                    default:
                        defecto++;
                        break;
                }
            }

            pieChartDataRosa = new PieChartData(addPieData(pieDataRosa, azul, gris, naranja, rojo, verde, defecto));
            pieChartDataRosa.setHasLabels(true);
            pieChartDataRosa.setHasCenterCircle(true)
                    .setCenterText1FontSize(16).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartViewRosa.setPieChartData(pieChartDataRosa);
        }

        if(estadisticasTurquesa.size() >0) {
            azul = 0;
            gris = 0;
            naranja = 0;
            rojo = 0;
            verde = 0;
            defecto = 0;

            for (int posicion : estadisticasTurquesa) {

                switch (posicion) {
                    case 0:
                        azul++;
                        break;
                    case 1:
                        gris++;
                        break;
                    case 2:
                        naranja++;
                        break;
                    case 3:
                        rojo++;
                        break;
                    case 4:
                        verde++;
                        break;
                    default:
                        defecto++;
                        break;
                }
            }


            pieChartDataTurquesa = new PieChartData(addPieData(pieDataTurquesa, azul, gris, naranja, rojo, verde, defecto));
            pieChartDataTurquesa.setHasLabels(true);
            pieChartDataTurquesa.setHasCenterCircle(true)
                    .setCenterText1FontSize(16).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartViewTurquesa.setPieChartData(pieChartDataTurquesa);
        }

        if(estadisticasNaranja.size() > 0) {
            azul = 0;
            gris = 0;
            naranja = 0;
            rojo = 0;
            verde = 0;
            defecto = 0;

            for (int posicion : estadisticasNaranja) {

                switch (posicion) {
                    case 0:
                        azul++;
                        break;
                    case 1:
                        gris++;
                        break;
                    case 2:
                        naranja++;
                        break;
                    case 3:
                        rojo++;
                        break;
                    case 4:
                        verde++;
                        break;
                    default:
                        defecto++;
                        break;
                }
            }

            pieChartDataNaranja = new PieChartData(addPieData(pieDataNaranja, azul, gris, naranja, rojo, verde, defecto));
            pieChartDataNaranja.setHasLabels(true);
            pieChartDataNaranja.setHasCenterCircle(true)
                    .setCenterText1FontSize(16).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartViewNaranja.setPieChartData(pieChartDataNaranja);
        }
        if(estadisticasGris.size() > 0) {
            azul = 0;
            gris = 0;
            naranja = 0;
            rojo = 0;
            verde = 0;
            defecto = 0;


            for (int posicion : estadisticasGris) {

                switch (posicion) {
                    case 0:
                        azul++;
                        break;
                    case 1:
                        gris++;
                        break;
                    case 2:
                        naranja++;
                        break;
                    case 3:
                        rojo++;
                        break;
                    case 4:
                        verde++;
                        break;
                    default:
                        defecto++;
                        break;
                }
            }

            pieChartDataGris = new PieChartData(addPieData(pieDataGris, azul, gris, naranja, rojo, verde, defecto));
            pieChartDataGris.setHasLabels(true);
            pieChartDataGris.setHasCenterCircle(true)
                    .setCenterText1FontSize(16).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartViewGris.setPieChartData(pieChartDataGris);
        }
    }

    private List<SliceValue> addPieData(List<SliceValue> pieData,int azul, int gris, int naranja, int rojo, int verde, int defecto){

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

        return pieData;

        //Pruebacambios

    }
}
