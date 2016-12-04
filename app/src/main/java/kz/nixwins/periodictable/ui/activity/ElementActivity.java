package kz.nixwins.periodictable.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import kz.nixwins.periodictable.R;
import kz.nixwins.periodictable.helper.Animator;
import kz.nixwins.periodictable.helper.ChartFormatter;
import kz.nixwins.periodictable.helper.ElementColor;
import kz.nixwins.periodictable.helper.ParseString;
import kz.nixwins.periodictable.model.Element;

/**
 * Created by nixwins on 11/23/16.
 */

public class ElementActivity extends AppCompatActivity {

    private TextView symbol;
    private TextView name;
    private TextView latinName;
    private TextView periodAndRow;
    private TextView groupAndSubgroup;
    private TextView atomicNumber;
    private TextView atomicWeight;
    private TextView elementConfiguration;
    private TextView oxide;
    private TextView volatileHydrogenid;
    private TextView electronFormula;
    private TextView elementValence;
    private LinearLayout element_name_wrapper;

    private Animator animator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_layout);

        Intent intent = getIntent();
        Element element = (Element) intent.getSerializableExtra("element");

        /* Setting Toolbar */

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView toolbarTitle;
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(element.getName());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        /* Binding Views */

        symbol                   = (TextView) findViewById(R.id.element_symbol);
        name                     = (TextView) findViewById(R.id.element_current_lang_name);
        latinName                = (TextView) findViewById(R.id.element_latin_name);
        periodAndRow             = (TextView) findViewById(R.id.element_period_row);
        groupAndSubgroup         = (TextView) findViewById(R.id.element_group_subgroup);
        atomicNumber             = (TextView) findViewById(R.id.element_atomic_number);
        atomicWeight             = (TextView) findViewById(R.id.element_atomic_weight);
        elementConfiguration     = (TextView) findViewById(R.id.element_configuration);
        oxide                    = (TextView) findViewById(R.id.element_oxide);
        volatileHydrogenid       = (TextView) findViewById(R.id.element_volatile_hydrogenid);
        electronFormula          = (TextView) findViewById(R.id.element_electron_formula);
        elementValence           = (TextView) findViewById(R.id.element_valence_text);

        element_name_wrapper    = (LinearLayout) findViewById(R.id.element_name_wrapper);

        setElementValues(element);

        animator = new Animator(getApplicationContext());

        // Блок работы с графикой

        PieChart chart = (PieChart) findViewById(R.id.chart);
        drawChart(chart, element);

        /*  Setting fonts */
        overrideFonts(this, findViewById(R.id.main_layout));

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        setViewAnimation();
    }

    private void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (v instanceof TextView ) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "font/Roboto-Light.ttf"));
            }
        } catch (Exception e) {
        }
    }

    private List<String> getEnergyLevel(String energyLvlStr){
        List<String> energyLvl = new ArrayList<>();

        StringTokenizer sTokenizer = new StringTokenizer(energyLvlStr, ",");

        while(sTokenizer.hasMoreTokens()){
            energyLvl.add(sTokenizer.nextToken());
        }

        return energyLvl;

    }

    private void setElementValues(Element element){

        symbol.setText(WordUtils.capitalize(element.getSymbol()));
        name.setText(WordUtils.capitalize(element.getName()));
        latinName.setText(WordUtils.capitalize(element.getLatinName()));
        periodAndRow.setText(element.getPeriod() + "\\" + element.getRow());
        groupAndSubgroup.setText(element.getGroup() + "\\" + element.getSubGroup());
        atomicNumber.setText(Integer.toString(element.getAtomicNumber()));
        atomicWeight.setText(Double.toString(element.getAtomicWeight()));
        elementConfiguration.setText(Html.fromHtml(ParseString.superscript(element.getElectronConfiguration())));
        oxide.setText(element.getOxide());
        volatileHydrogenid.setText(element.getVolatileHydrogenid());
        electronFormula.setText(Html.fromHtml(ParseString.superscript(element.getElectronFormula())));
        elementValence.setText(element.getValence());

        /*Settings element*/

        ElementColor.setElementBackgroundColor(element.getElectronEnergyType(), symbol);
        ElementColor.setElementBackgroundColor(element.getElectronEnergyType(), name);
        ElementColor.setElementBackgroundColor(element.getElectronEnergyType(), latinName);
        ElementColor.setElementBackgroundColor(element.getElectronEnergyType(), element_name_wrapper);
    }

    private void setViewAnimation(){

        //animator.animLeftToRight(symbol);
        animator.animLeftToRight(periodAndRow);
        animator.animLeftToRight(atomicWeight);
        animator.animLeftToRight(name);
        animator.animLeftToRight(elementConfiguration);
        animator.animLeftToRight(oxide);

        animator.animRightToLeft(groupAndSubgroup);
        animator.animRightToLeft(atomicNumber);
        animator.animRightToLeft(latinName);
        animator.animRightToLeft(volatileHydrogenid);
    }

    private void drawChart(PieChart chart, Element element){

        List<PieEntry> entries = new ArrayList<>();

        List dataObject = getEnergyLevel(element.getElectronEnergyLevel());

     /*   Log.d("SIze", Integer.toString(dataObject.size()));
        for(int i=0; i<dataObject.size(); i++)
            Log.d("LEVEL", dataObject.get(i).toString());*/
        List<String> formula = element.getFormulaAsList();

        for(int i=0; i < dataObject.size(); i++){

            entries.add(new PieEntry(Integer.valueOf(dataObject.get(i).toString()), getResources().getString(R.string.eachLabelForPieChart) + " " + (i+1)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        PieData barData = new PieData(dataSet);

        dataSet.setValueFormatter(new ChartFormatter());
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextSize(16F);

        chart.setCenterText(getResources().getString(R.string.pieChartCenterText));
        chart.setData(barData);
        chart.setHoleColor(Color.BLACK);
        chart.setCenterTextColor(Color.WHITE);
        Legend legend = chart.getLegend();
        legend.setTextColor(Color.WHITE);
        legend.setTextSize(10F);
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        legend.setWordWrapEnabled(true);
        chart.animateY(4000);

    }
}