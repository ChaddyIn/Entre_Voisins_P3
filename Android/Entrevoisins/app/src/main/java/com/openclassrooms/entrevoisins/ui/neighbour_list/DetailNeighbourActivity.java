package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.io.Serializable;
import java.util.List;

public class DetailNeighbourActivity extends AppCompatActivity implements Serializable {
    ImageView detailImageNeighbour;
    TextView detailPrenomNeighbour;
    TextView textDetailNeighbour;
    TextView detailPrenom;
    TextView detailAdresse;
    TextView detailTel;
    TextView detailFacebook;
    ImageButton backButton;

    FloatingActionButton buttonFav;

    public Boolean isFavNeighbour;

    private NeighbourApiService mApiServiceFav;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);

        mApiServiceFav = DI.getNeighbourApiService();


        detailImageNeighbour = (ImageView) findViewById(R.id.imageDetailNeighbourView);
        detailPrenomNeighbour = (TextView) findViewById(R.id.detailPrenomNeighbour);
        textDetailNeighbour = (TextView) findViewById(R.id.textDetailNeighbour);
        detailAdresse = (TextView) findViewById(R.id.detailAdresse);
        detailPrenom = (TextView) findViewById(R.id.detailPrenom);
        detailTel = (TextView) findViewById(R.id.detailTel);
        detailFacebook = (TextView) findViewById(R.id.detailFacebook);
        backButton = (ImageButton) findViewById(R.id.backButton);
        buttonFav = (FloatingActionButton) findViewById(R.id.ButtonFav);

        String DetailAboutNeigbourRecup = getIntent().getStringExtra(NeighbourFragment.DETAIL_TEXT_NEIGHBOUR);
        String UrlImageNeighbour = getIntent().getStringExtra(NeighbourFragment.AVATAR_NEIGHBOUR);
        String DetailPrenom = getIntent().getStringExtra(NeighbourFragment.DETAIL_PRENOM);
        String DetailAdresse = getIntent().getStringExtra(NeighbourFragment.DETAIL_ADRESSE);
        String DetailTel = getIntent().getStringExtra(NeighbourFragment.DETAIL_TEL);
        Neighbour currentNeighbour = (Neighbour) getIntent().getExtras().getSerializable(NeighbourFragment.CURRENT_OBJECT);


        if (mApiServiceFav.getFavNeighbours().contains(currentNeighbour)) {
            buttonFav.setImageResource(R.drawable.icone_favoris_24dp);
        }


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        buttonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mApiServiceFav.getFavNeighbours().contains(currentNeighbour)) {

                    mApiServiceFav.addFavoriteNeighbour(currentNeighbour);


                    if (mApiServiceFav.getFavNeighbours().contains(currentNeighbour)) {
                        buttonFav.setImageResource(R.drawable.icone_favoris_24dp);
                    }


                } else {
                    mApiServiceFav.deleteFavoriteNeighbour(currentNeighbour);

                    buttonFav.setImageResource(R.drawable.ic_favorite_24dp);
                }


            }
        });

        detailPrenomNeighbour.setText(DetailPrenom);
        textDetailNeighbour.setText(DetailAboutNeigbourRecup);
        detailPrenom.setText(DetailPrenom);
        detailAdresse.setText(DetailAdresse);
        detailAdresse.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_location_14dp, 0, 0, 0);
        detailTel.setText(DetailTel);
        detailTel.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.icone_phone_14dp, 0, 0, 0);
        detailFacebook.setText("www.facebook.fr/" + DetailPrenom.toLowerCase());
        detailFacebook.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.icone_facebook_14dp, 0, 0, 0);
        Glide.with(getApplicationContext()).load(UrlImageNeighbour).into(detailImageNeighbour);

    }

}





