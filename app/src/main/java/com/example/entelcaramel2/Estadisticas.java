package com.example.entelcaramel2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.entelcaramel2.Objetos.Caramelo;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Estadisticas extends AppCompatActivity {

    private static final String ENVOLTORIO = "envoltorio";
    private static final String CARAMELO = "caramelo";
    private int envoltorio = 0, sabor = 0;
    private PieChart pieChart;
    private String[] colores = new String[]{"Azul", "Gris", "Naranja", "Rojo", "Verde"};
    private ArrayList<Integer> envoltorios = new ArrayList<>();
    private ArrayList<Integer> sabores = new ArrayList<>();
    private ArrayList<Integer> estadisticas = new ArrayList<>();
    int cont = 0;
    private int[] color = new int[]{Color.rgb(25,50,225), Color.GRAY,Color.rgb(255,120,25),Color.RED,Color.rgb(25,200,25)};
    private DatabaseReference fireDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        envoltorio = getIntent().getExtras().getInt(ENVOLTORIO);
        sabor = getIntent().getExtras().getInt(CARAMELO);

        pieChart = findViewById(R.id.pieChart);

        fireDB = FirebaseDatabase.getInstance().getReference().child("entelcaramel2");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot nodo : dataSnapshot.getChildren()){
                    envoltorios.add(Integer.parseInt(nodo.child("envoltorio").getValue().toString()));
                    sabores.add(Integer.parseInt(nodo.child("sabor").getValue().toString()));

                    //if(envoltorios.get(cont) == envoltorio){
                        estadisticas.add(sabores.get(cont));
                    //}

                    cont = cont+1;
                }
                createCharts();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        fireDB.addListenerForSingleValueEvent(eventListener);


    }

    /**
     * Establece parametros de la leyenda
     * @param chart
     * @param description
     * @param textColor
     * @param bgColor
     * @param animateTime
     * @return
     */
    private Chart getSameChart(Chart chart, String description, int textColor, int bgColor, int animateTime) {
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(33);
        chart.getDescription().setTextColor(Color.WHITE);
        chart.setBackgroundColor(bgColor);
        chart.animateY(animateTime);
        legend(chart);
        return chart;
    }

    /**
     * Establece la leyenda
     * @param chart
     */
    private void legend(Chart chart) {

        Legend l = chart.getLegend();
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setTextSize(18);
        l.setTextColor(Color.WHITE);

        ArrayList<LegendEntry> entries = new ArrayList<>();
        for (int i = 0; i < colores.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = color[i];
            entry.label = colores[i];
            entry.formSize = 20.0f;
            entries.add(entry);
        }
        l.setCustom(entries);
    }

    private ArrayList<PieEntry> getPieEntries() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < estadisticas.size(); i++) {
            entries.add(new PieEntry(i, estadisticas.get(i)));
        }
        return entries;
    }

    private void axisX(XAxis axis) {
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(colores));
        axis.setEnabled(false);

    }

    private void axisLeft(YAxis axis) {
        axis.setSpaceTop(30);
        axis.setAxisMinimum(0);
    }

    private void axisRight(YAxis axis) {
        axis.setEnabled(false);
    }

    /**
     * Crea el gráfico
     */
    public void createCharts() {
        pieChart = (PieChart) getSameChart(pieChart, "Estadisticas", Color.WHITE, Color.DKGRAY,3000);
        pieChart.setHoleRadius(40);
        pieChart.setHoleColor(Color.DKGRAY);
        pieChart.setTransparentCircleRadius(105);
        pieChart.setTransparentCircleAlpha(10);
        pieChart.setData(getPieData());
        pieChart.invalidate();
        //pieChart.setDrawHoleEnabled(false);
    }

    /**
     * Trata los datos de la tarta
     * @param dataSet
     * @return
     */
    private DataSet getData(DataSet dataSet) {
        dataSet.setColors(color);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(18);
        dataSet.setDrawValues(false);
        return dataSet;
    }

    /**
     * Trata datos de las porciones
     * @return
     */
    private PieData getPieData() {
        PieDataSet pieDataSet = (PieDataSet) getData(new PieDataSet(getPieEntries(),""));
        pieDataSet.setSliceSpace(25);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}
