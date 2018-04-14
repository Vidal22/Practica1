package com.example.practica1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.practica1.rankingpackage.GameSelectActivity;
import com.example.practica1.rankingpackage.RankingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnJugar = (Button) findViewById(R.id.btnJugar);

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("vidi", "onClick()");
                Intent intent = new Intent(MainActivity.this, JugarActivity.class);
                startActivity(intent);

            }
        });

        Button btnRanq = (Button) findViewById(R.id.btnRanq);

        btnRanq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("vidi", "onClick()");
                Intent intent2 = new Intent(MainActivity.this,GameSelectActivity.class);
                startActivity(intent2);
            }
        });

        Button btnAjus = (Button) findViewById(R.id.btnAjus);

        btnAjus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("vidi","onClick()");
                Intent intent3 = new Intent(MainActivity.this, AjusActivity.class);
                startActivity(intent3);
            }
        });

        Button btnInfo = (Button) findViewById(R.id.btnInfo);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("vidi", "onClick()");
                Intent intent4 = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent4);
            }
        });


    }



}
