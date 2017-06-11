package com.herxlabs.politix.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.herxlabs.politix.BuildConfig;
import com.herxlabs.politix.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.appVersion)    TextView appVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        String versionName = BuildConfig.VERSION_NAME;
        appVersion.setText("Politix v" + versionName);
    }
}
