package com.example.entelcaramel2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

import java.util.ArrayList;

public class Estadisticas extends AppCompatActivity {

    private PieChart pieChart;

    private String[] colores = new String[]{"verde", "azul", "amarillo", "rojo", "gris"};
    private int[] datos = new int[]{5, 10, 15, 20, 25};
    private int[] color = new int[]{Color.YELLOW, Color.RED,Color.rgb(0,150,100),Color.BLUE,Color.rgb(255,100,0)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        pieChart = findViewById(R.id.pieChart);
        createCharts();
    }

    private Chart getSameChart(Chart chart, String description, int textColor, int bgColor, int animateTime) {
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(bgColor);
        chart.animateY(animateTime);
        legend(chart);
        return chart;
    }

    private void legend(Chart chart) {
        Legend l = chart.getLegend();
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries = new ArrayList<>();
        for (int i = 0; i < colores.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = color[i];
            entry.label = colores[i];
            entries.add(entry);
        }
        l.setCustom(entries);
    }

    private ArrayList<PieEntry> getPieEntries() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < datos.length; i++) {
            entries.add(new PieEntry(i, datos[i]));
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

    private DataSet getData(DataSet dataSet) {
        dataSet.setColors(color);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(16);
        return dataSet;
    }

    private PieData getPieData() {
        PieDataSet pieDataSet = (PieDataSet) getData(new PieDataSet(getPieEntries(),""));
        pieDataSet.setSliceSpace(10);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }
}
