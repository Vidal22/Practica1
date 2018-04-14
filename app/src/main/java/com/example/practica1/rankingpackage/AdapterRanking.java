package com.example.practica1.rankingpackage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practica1.R;
import com.example.practica1.rankingpackage.Model.Ranking;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Vidiic on 31/12/2017.
 */

public class AdapterRanking extends RecyclerView.Adapter<AdapterRanking.ViewHolder>
        implements View.OnClickListener{

    List<Ranking> rankings;
    Context context;
    View.OnClickListener listener;

    public AdapterRanking(List<Ranking> rankings, Context context) {
        this.rankings = rankings;
        this.context = context;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public TextView id_user;
        public TextView score;

        ViewHolder(View view){
            super(view);
            imagen=view.findViewById(R.id.idRank);
            id_user=view.findViewById(R.id.userId);
            score=view.findViewById(R.id.score);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.viewholder_player,parent,false);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Ranking ranking = rankings.get(position);
        Picasso.with(context).load(ranking.getPlayer().getImagen()).into(holder.imagen);
        holder.score.setText(ranking.getScore());
        holder.id_user.setText(ranking.getPlayer().getUsername());

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
    public int getItemCount() {
        return rankings.size();
    }

}
