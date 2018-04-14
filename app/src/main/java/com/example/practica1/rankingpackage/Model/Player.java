package com.example.practica1.rankingpackage.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vidiic on 01/01/2018.
 */

public class Player {

    String id;
    String username;
    @SerializedName("avatar_path")
    String imagen;

    public Player(String id, String username, String imagen) {
        this.id = id;
        this.username = username;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getImagen() {
        return imagen;
    }
}
