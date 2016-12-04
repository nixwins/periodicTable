package kz.nixwins.periodictable.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kz.nixwins.periodictable.R;
import kz.nixwins.periodictable.adapter.ElementAdapter;
import kz.nixwins.periodictable.model.Element;
import kz.nixwins.periodictable.model.ElementSQL;


/**
 * Created by nixwins on 11/23/16.
 */

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private RecyclerView        recyclerView;
    private ElementAdapter      elementAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Element>       elements;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
            setContentView(R.layout.main_layout);

        ElementSQL elementSQL = new ElementSQL(this);
        elements              = elementSQL.getAll();
        Collections.sort(elements);

        recyclerView = (RecyclerView) findViewById(R.id.periodicTableRc);
        linearLayoutManager = new LinearLayoutManager(this);
        elementAdapter      = new ElementAdapter(elements);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(elementAdapter);
        //recyclerView.setItemAnimator(new SlideInLeftAnimator());
        recyclerView.setHasFixedSize(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LauncherActitvity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getResources().getString(R.string.hint_search));
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.trim();
        if(!newText.isEmpty())
            filter(newText);
        else if(newText.isEmpty())
            elementAdapter.updateList(elements);

        return true;
    }

    void filter(String text){
        //Log.d("FILTER TEXT", text);
        text = text.toLowerCase();

        List<Element> temp = new ArrayList();
        for(Element d: elements){
            if(d.getLatinName().toLowerCase().contains(text) || d.getName().toLowerCase().contains(text)){
                temp.add(d);
            }
        }
        //update recyclerview
        elementAdapter.updateList(temp);
    }

}