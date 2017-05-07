package com.herxlabs.politix;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.herxlabs.politix.diputados.DiputadosActivity;
import com.herxlabs.politix.favs.FavsActivity;
import com.herxlabs.politix.senadores.SenadoresActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.persona)
    public void persona() {
        Intent intent = new Intent(this, PersonActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.fav)
    public void fav() {
        Intent intent = new Intent(this, FavsActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.diputados)
    public void dip() {
        Intent intent = new Intent(this, DiputadosActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.senadores)
    public void sen() {
        Intent intent = new Intent(this, SenadoresActivity.class);
        startActivity(intent);
    }
}
