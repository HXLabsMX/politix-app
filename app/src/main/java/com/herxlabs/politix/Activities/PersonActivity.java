package com.herxlabs.politix.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.herxlabs.politix.Models.Politico;
import com.herxlabs.politix.R;
import com.herxlabs.politix.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonActivity extends AppCompatActivity {
    private Politico politico;
    @BindView(R.id.persona_nombre)    TextView pNombre;
    @BindView(R.id.persona_foto)    ImageView pfoto;
    @BindView(R.id.persona_descripcion)    TextView pDescripcion;
    @BindView(R.id.txtTwitter)    TextView pTwitter;
    @BindView(R.id.txtFacebook)    TextView pFacebook;
    @BindView(R.id.txtYoutube)    TextView pYoutube;
    @BindView(R.id.txtCorreo)    TextView pCorreo;
    @BindView(R.id.twitter)    CardView cardTwitter;
    @BindView(R.id.facebook)    CardView cardFacebook;
    @BindView(R.id.youtube)    CardView cardYoutube;
    @BindView(R.id.correo)    CardView cardCorreo;
    @BindView(R.id.social)    LinearLayout lSocial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        politico = (Politico) getIntent().getSerializableExtra("POLITICO");

        pNombre.setText(politico.getNombre());
        pfoto.setImageResource(Utils.getDrawableId(politico.getFoto()));
        pDescripcion.setText(politico.getEstado());

        if(politico.getTwitter().isEmpty()) lSocial.removeView(cardTwitter);
        if(politico.getFacebook().isEmpty()) lSocial.removeView(cardFacebook);
        if(politico.getYoutube().isEmpty()) lSocial.removeView(cardYoutube);
        if(politico.getEmail().isEmpty()) lSocial.removeView(cardCorreo);

//        pTwitter.setText(politico.getTwitter());
//        pFacebook.setText(politico.getFacebook());
//        pYoutube.setText(politico.getYoutube());
//        pCorreo.setText(politico.getEmail());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Marcado: " + politico.getEmail(), Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
