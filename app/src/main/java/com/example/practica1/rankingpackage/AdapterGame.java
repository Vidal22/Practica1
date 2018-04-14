package com.example.practica1.rankingpackage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practica1.R;
import com.example.practica1.rankingpackage.Model.Game;

import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by Vidiic on 06/01/2018.
 */

public class AdapterGame extends RecyclerView.Adapter<AdapterGame.ViewHolder>
        implements View.OnClickListener{

    private List<Game> games;
    Context context;
    View.OnClickListener listener;

    public AdapterGame(List<Game> games, Context context) {
        this.games = games;
        this.context = context;
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imagenGame;
        public TextView nombreGame;
        public TextView descripcionGame;

        public ViewHolder(View view) {
            super(view);

            imagenGame = view.findViewById(R.id.imagenGame);
            nombreGame = view.findViewById(R.id.nombreGame);
            descripcionGame = view.findViewById(R.id.descripcionGame);

        }
    }

    @Override
    public AdapterGame.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.viewholder_game,parent,false);

        itemView.setOnClickListener(this);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(AdapterGame.ViewHolder holder, int position) {

        Game game = games.get(position);
        Picasso.with(context).load(game.getImagen()).into(holder.imagenGame);
        holder.nombreGame.setText(game.getName());
        holder.descripcionGame.setText(game.getDescription());

    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    @Override
    public int getItemCount() {return games.size();}
}