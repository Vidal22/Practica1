package com.example.practica1.rankingpackage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.practica1.R;
import com.example.practica1.rankingpackage.Model.Game;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameSelectActivity extends AppCompatActivity
        implements GameAsyncTask.WeakReference {

    List<Game> games;
    AdapterGame adapter;
    RecyclerView rcGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);

        GameAsyncTask gameAsyncTask = new GameAsyncTask(this);
        gameAsyncTask.execute("1");
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finished(Game[] list) {

        games = new ArrayList<>(Arrays.asList(list));

        adapter = new AdapterGame(games,this);

        rcGame = findViewById(R.id.recyclerViewGame);

        RecyclerView.LayoutManager layoutManager  =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);

        rcGame.setLayoutManager(layoutManager);

        rcGame.setItemAnimator(new DefaultItemAnimator());

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GameSelectActivity.this, RankingActivity.class);
                //intent.putExtra("game",games.get(rcGame.getChildAdapterPosition(view)).getId());
                Game gameEnviar = games.get(rcGame.getChildAdapterPosition(view));
                //Toast.makeText(getApplicationContext(),gameEnviar.toString(),Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putSerializable("game",gameEnviar);
                intent.putExtras(bundle);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),"Seleccion"+games.get(rcGame.getChildAdapterPosition(view)).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        rcGame.setAdapter(adapter);
    }

}

