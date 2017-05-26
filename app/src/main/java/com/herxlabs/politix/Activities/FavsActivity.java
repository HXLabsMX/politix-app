package com.herxlabs.politix.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.herxlabs.politix.Adapters.FavsAdapter;
import com.herxlabs.politix.Models.Politico;
import com.herxlabs.politix.R;
import com.herxlabs.politix.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class FavsActivity extends AppCompatActivity {
    private List<Politico> politicosList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FavsAdapter pAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        pAdapter = new FavsAdapter(politicosList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pAdapter);

        loadFavs();
    }


    private void loadFavs() {
        Politico politico = new Politico("Alberto", "Adventure", "2015");
        politicosList.add(politico);

        politico = new Politico("Felipe", "Family", "2015");
        politicosList.add(politico);

        politico = new Politico("Star", "Action", "2015");
        politicosList.add(politico);

        politico = new Politico("Sheep", "Animation", "2015");
        politicosList.add(politico);

        politico = new Politico("Martian", "Fantasy", "2015");
        politicosList.add(politico);

        politico = new Politico("Mission", "Action", "2015");
        politicosList.add(politico);

        politico = new Politico("Up", "Animation", "2009");
        politicosList.add(politico);


        pAdapter.notifyDataSetChanged();
    }
}
