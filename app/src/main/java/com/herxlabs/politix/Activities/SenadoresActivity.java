package com.herxlabs.politix.Activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.herxlabs.politix.Adapters.SenadoresAdapter;
import com.herxlabs.politix.Models.Politico;
import com.herxlabs.politix.R;
import com.herxlabs.politix.utils.RecyclerItemClickListener;
import com.herxlabs.politix.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SenadoresActivity extends AppCompatActivity  {
    private List<Politico> senadoresList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SenadoresAdapter pAdapter;
    private ProgressDialog pDialog;
    @BindView(R.id.txtSearch) TextView txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senadores);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        recyclerView = (RecyclerView) findViewById(R.id.sen_recycler_view);
        //recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Intent intent = new Intent(getApplicationContext(), PersonActivity.class);
                        intent.putExtra("POLITICO", pAdapter.getSelected(position));
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    filter();
                    return true;
                }
                return false;
            }
        });
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
        if (id == R.id.action_byName) {
            pAdapter.sortByNombre();
            return true;
        }else if (id == R.id.action_byState) {
            pAdapter.sortByEstado();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void filter(){
        String text = txtSearch.getText().toString();
        pAdapter.getFilter().filter(text);
        recyclerView.scrollToPosition(0);
        recyclerView.setAlpha(0.0f);
        recyclerView.animate()
                .translationY(0)
                .setDuration(300)
                .alpha(1.0f)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

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
                json_file = Utils.openJson("senadores", getApplicationContext());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (json_file != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json_file);

                    // Getting JSON Array node
                    JSONArray json_result = jsonObj.getJSONArray("senadores");


                    for (int i = 0; i < json_result.length(); i++) {
                        JSONObject c = json_result.getJSONObject(i);
                        String nombre = c.optString("nombre");
                        String apellido = c.optString("apellido");
                        Politico politico = new Politico(nombre ,nombre, apellido);
                        politico.setFoto(c.optString("foto").toLowerCase());
                        politico.setEmail(c.optString("email"));
                        politico.setEstado(c.optString("estado"));
                        politico.setFacebook(c.optString("facebook"));
                        politico.setTwitter(c.optString("twitter"));
                        politico.setYoutube(c.optString("yt"));
                        senadoresList.add(politico);
                    }

                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();

            pAdapter = new SenadoresAdapter(getApplicationContext(),senadoresList);
            recyclerView.setAdapter(pAdapter);
            pAdapter.sortByNombre();
            pAdapter.notifyDataSetChanged();
            txtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    // TODO Auto-generated method stub
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    // TODO Auto-generated method stub
                }

                @Override
                public void afterTextChanged(Editable s) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        filter();
                    }
                }, 450);
                }
            });
        }

    }
}
