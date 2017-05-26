package com.herxlabs.politix.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.herxlabs.politix.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.favs)
    public void favoritos() {
        Intent intent = new Intent(this, FavsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.senadores)
    public void senadores() {
        Intent intent = new Intent(this, SenadoresActivity.class);
        startActivity(intent);
    }
}
