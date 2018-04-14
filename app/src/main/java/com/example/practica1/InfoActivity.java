package com.example.practica1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {


    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);


        //Grup de botons
        ImageView btnimg1 = (ImageView)findViewById(R.id.img1);
        ImageView btnimg2 = (ImageView)findViewById(R.id.img2);
        ImageView btnimg3 = (ImageView)findViewById(R.id.img3);
        ImageView btnimg4 = (ImageView)findViewById(R.id.img4);
        ImageView btnimg5 = (ImageView)findViewById(R.id.img5);
        ImageView btnimg6 = (ImageView)findViewById(R.id.img6);
        //#1


        ImageButton btnInicio = (ImageButton) findViewById(R.id.bntInicio);

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


       btnimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.facebook.com/");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent1);

            }
        });

        btnimg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.instagram.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btnimg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.twitter.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btnimg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.snapchat.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btnimg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.github.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btnimg6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.youtube.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });/**/
    }@Override
    protected  void onResume(){
        super.onResume();
        Log.d("vidi", "resume");
        //Restaurem Dades
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

       Float stars = prefs.getFloat("stars",0);

       ratingBar.setRating(stars);


    }@Override
    protected void  onPause(){
        Log.d("vidi", "pause");
        //DesemDades
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed = prefs.edit();
        Float stars = ratingBar.getRating();
        ed.putFloat("stars", stars);

        ed.apply();
        super.onPause();

    }
}
