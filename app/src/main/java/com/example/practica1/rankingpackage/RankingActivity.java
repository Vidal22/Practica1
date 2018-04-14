package com.example.practica1.rankingpackage;



import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practica1.R;
import com.example.practica1.rankingpackage.Model.Game;
import com.example.practica1.rankingpackage.Model.Ranking;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RankingActivity extends AppCompatActivity implements RankingAsyncTask.WeakReference{

    String idgame="1";
    List<Ranking> rankings;
    AdapterRanking adapter;
    static ImageView imageView;
    static TextView nombre,description;
    RecyclerView rcSearch;
    SwipeRefreshLayout swiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        nombre = findViewById(R.id.nombreJuego);
        description = findViewById(R.id.description);
        imageView = findViewById(R.id.image);
        swiperefresh = findViewById(R.id.swipe_refresh);

        RankingAsyncTask task = new RankingAsyncTask(this);
        Bundle objetoEnviado = getIntent().getExtras();
        Game game = null;
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {refreshUpdate();}
        });

        if (objetoEnviado != null){
            game = (Game)objetoEnviado.getSerializable("game");
            Picasso.with(getApplicationContext()).load(game.getImagen()).into(imageView);
            nombre.setText(game.getName());
            description.setText(game.getDescription());
            idgame=game.getId();
            task.execute(idgame);
        }

    }
    public void refreshUpdate(){
        RankingAsyncTask task = new RankingAsyncTask(this);
        task.execute(idgame);
        Log.d("vidi", "start refresh");
    }
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finished(Ranking[] list) {
        swiperefresh.setRefreshing(false);
        rankings = new ArrayList<>(Arrays.asList(list));

        adapter = new AdapterRanking(rankings, getApplicationContext());

        rcSearch = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager  =
                new LinearLayoutManager(getApplicationContext());

        rcSearch.setLayoutManager(layoutManager);

        rcSearch.setItemAnimator(new DefaultItemAnimator());

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("vidi", "HEYY");

            }
        });

        rcSearch.setAdapter(adapter);

    }
}
