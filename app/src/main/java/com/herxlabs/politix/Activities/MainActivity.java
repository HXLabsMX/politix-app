package com.herxlabs.politix.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

    @OnClick(R.id.senadores)
    public void senadores() {
        Intent intent = new Intent(this, SenadoresActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_donar) {
            openDonations();
            return true;
        }else if (id == R.id.action_acercade) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void openDonations(){
        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.me/HerXLabsDonation/5"));
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Por favor instala un navegador web.",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
