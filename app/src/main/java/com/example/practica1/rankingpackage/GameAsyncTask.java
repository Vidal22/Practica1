package com.example.practica1.rankingpackage;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.practica1.rankingpackage.Model.Game;

import com.google.gson.Gson;

import org.json.JSONArray;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Vidiic on 06/01/2018.
 */

public class GameAsyncTask extends AsyncTask<String,Integer,Game[]> {

    private static String data="";
    private Game[] games;

    interface WeakReference {
        Context getContext();
        void finished(Game[] list);
    }
    private WeakReference ref;

    public GameAsyncTask(WeakReference ref) {
        super();
        this.ref = ref;
    }
    @Override
    protected Game[] doInBackground(String... strings) {

        InputStream in = null;
        try {
            String search = strings[0];
            search = URLEncoder.encode(search, "utf-8");

            URL url = new URL("http://stucom.flx.cat/game/api/game");

            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(1000);
            int length = conn.getContentLength();
            Log.d("vidi", "ContentLength = " +length);

            in = url.openStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int total = 0, nRead;

            while ((nRead = in.read(buffer)) != -1){
                outputStream.write(buffer,0,nRead);
                total += nRead;
            }

            String json = new String(outputStream.toByteArray());
            Gson gson = new Gson();

            JSONObject fullObject = new JSONObject(json);

            JSONArray jsonArray = fullObject.getJSONArray("data");

            games=gson.fromJson(jsonArray.toString(), Game[].class);

            for(Game set: games) {
                Log.d("vidi", set.getId() + " : " + set.getName());
            }

            return games;

        } catch (Exception e) {
            e.getMessage();
            return null;
        }
        finally {
            try { if (in!=null) in.close(); } catch (IOException ignored) {}
        }
    }
    @Override
    public void onPostExecute(Game[] result) {

        ref.finished(result);
    }
}