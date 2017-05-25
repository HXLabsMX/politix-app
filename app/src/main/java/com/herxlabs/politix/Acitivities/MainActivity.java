package com.herxlabs.politix.Acitivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.herxlabs.politix.R;
import com.herxlabs.politix.Utils.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    private Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        utils = new Utils(this);

    }
    @OnClick(R.id.persona)
    public void persona() {
        Intent intent = new Intent(this, SenadoresActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.fav)
    public void fav() {
        Intent intent = new Intent(this, FavsActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.diputados)
    public void dip() {
        Intent intent = new Intent(this, SenadoresActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.senadores)
    public void sen() {
        Intent intent = new Intent(this, SenadoresActivity.class);
        startActivity(intent);
    }
}
