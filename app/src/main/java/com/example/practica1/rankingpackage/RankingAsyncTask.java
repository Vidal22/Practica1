package com.example.practica1.rankingpackage;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.practica1.rankingpackage.Model.Ranking;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vidiic on 04/01/2018.
 */
//Game 1 o 2
public class RankingAsyncTask extends AsyncTask<String,Integer,Ranking[]> {

    private Ranking[] rankings;

    interface WeakReference {
        Context getContext();
        void finished(Ranking[] list);
    }
    private WeakReference ref;
    public RankingAsyncTask(WeakReference ref) {
        super();
        this.ref = ref;
    }

    @Override
    protected Ranking[] doInBackground(String... strings) {

        InputStream in = null;
        try {

            String search = strings[0];
            search = URLEncoder.encode(search, "utf-8");

            URL url = new URL("http://stucom.flx.cat/game/api/game/"+search);

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
            JSONObject gameJson = fullObject.getJSONObject("data");

            JSONArray jsonArray = gameJson.getJSONArray("ranking");

            rankings = gson.fromJson(jsonArray.toString(), Ranking[].class);

            /*for(Ranking set: rankings) {
                 Log.d("vidi", set.getId() + " : " + set.getPlayer().getUsername());
              }*/
            return rankings;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
        finally {
            try { if (in!=null) in.close(); } catch (IOException ignored) {}
        }
    }@Override
    public void onPostExecute(Ranking[] result) {

        ref.finished(result);

    }



}