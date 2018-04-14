package com.example.practica1.rankingpackage.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vidiic on 31/12/2017.
 */

public class Ranking {
    private String id;
    private String user_id;
    private String score;
    @SerializedName("user")
    private Player player;

    public Ranking(String id, String user_id, String score,Player player) {
        this.id = id;
        this.user_id = user_id;
        this.score = score;
        this.player=player;
    }

    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getScore() {
        return score;
    }

    public Player getPlayer(){return player;}
}
