package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;

import org.w3c.dom.Text;

import butterknife.BindView;

public class DetailNeighbourActivity extends AppCompatActivity {

    ImageView detailImageNeighbour;
    TextView textDetailNeighbour;
    TextView detailPrenom;
    TextView detailAdresse;
    TextView detailTel;
    TextView detailFacebook;








    protected void onCreate(Bundle savedInstanceState) {





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);

        detailImageNeighbour = (ImageView) findViewById(R.id.imageDetailNeighbourView);
        textDetailNeighbour = (TextView) findViewById(R.id.textDetailNeighbour);
        detailAdresse = (TextView) findViewById(R.id.detailAdresse);
        detailPrenom = (TextView) findViewById(R.id.detailPrenom);
        detailTel = (TextView) findViewById(R.id.detailTel);
        detailFacebook = (TextView) findViewById(R.id.detailFacebook);


        String DetailAboutNeigbourRecup = getIntent().getStringExtra(NeighbourFragment.DETAIL_TEXT_NEIGHBOUR);
        String UrlImageNeighbour = getIntent().getStringExtra(NeighbourFragment.AVATAR_NEIGHBOUR);
        String DetailPrenom = getIntent().getStringExtra(NeighbourFragment.DETAIL_PRENOM);
        String DetailAdresse = getIntent().getStringExtra(NeighbourFragment.DETAIL_ADRESSE);
        String DetailTel = getIntent().getStringExtra(NeighbourFragment.DETAIL_TEL);

        textDetailNeighbour.setText(DetailAboutNeigbourRecup);
        detailPrenom.setText(DetailPrenom);
        detailAdresse.setText(DetailAdresse);
        detailTel.setText(DetailTel);
        Glide.with(getApplicationContext()).load(UrlImageNeighbour).into(detailImageNeighbour);





    }
}





