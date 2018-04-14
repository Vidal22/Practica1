package com.example.practica1.rankingpackage.Model;

import java.io.Serializable;

/**
 * Created by Vidiic on 01/01/2018.
 */

public class Game implements Serializable {

    private String id;
    private String name;
    private String description;
    private String image_path;

    public Game(String id, String name, String description, String image_path) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_path = image_path;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImagen() {
        return image_path;
    }

}
