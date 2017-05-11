package com.herxlabs.politix.senadores;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bluelinelabs.logansquare.LoganSquare;
import com.herxlabs.politix.R;
import com.herxlabs.politix.model.Politico;
import com.herxlabs.politix.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

public class SenadoresActivity extends AppCompatActivity {

    private List<Politico> politicosList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SenadoresAdapter pAdapter;
    private Utils utils;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senadores);
        ButterKnife.bind(this);

        utils = new Utils(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.sen_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        new GetSenadores().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_senadores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void senParsing() {
        final List<Politico> politicos;
        try {
            String json_file = Utils.openJson("senadores", utils.getContext());
            //Log.d("fileH",json_file);
            politicos = LoganSquare.parseList(json_file, Politico.class);
            politicosList = politicos;
            Log.d("fileH", politicosList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        pAdapter.notifyDataSetChanged();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetSenadores extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(SenadoresActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            String json_file = "";
            try {
                json_file = Utils.openJson("senadores", utils.getContext());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (json_file != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json_file);

                    // Getting JSON Array node
                    JSONArray json_result = jsonObj.getJSONArray("senadores");


                    for (int i = 0; i < json_result.length(); i++) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        JSONObject c = json_result.getJSONObject(i);
                        String nombre = c.optString("nombre");
                        String apellido = c.optString("apellido");
                        String estado = c.optString("estado");
                        Politico politico = new Politico(nombre ,apellido, estado);
                        politicosList.add(politico);
                    }

                } catch (final JSONException e) {
////                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG)
//                                    .show();
//                        }
//                    });

                }
            } else {
//                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG)
//                                .show();
//                    }
//                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            pAdapter = new SenadoresAdapter(politicosList);
            recyclerView.setAdapter(pAdapter);
            pAdapter.notifyDataSetChanged();;
        }

    }
}
