package com.example.practica1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AjusActivity extends AppCompatActivity {
    ImageView imageView;
    private static final int SELECT_FILE = 1;
    private static final int SELECT_FILE2 = 200;
    private String encodedImage = "";
    private String encodedImageOld="";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajus);

        editText = (EditText) findViewById(R.id.TextNom);


        ImageButton btnCamara1 = (ImageButton) findViewById(R.id.btnCamara);

        btnCamara1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) !=null){
                    startActivityForResult(takePictureIntent, SELECT_FILE2);
                }
            }
        });

        //IMPORTANT
        ///Les imatges verticals es veuran perfectament pero las horitzontals segons quina mida tinguin no es podran veure
        //perque el imageview que he personalitzat es bastant limitat.

        ImageButton btnGaleria = (ImageButton) findViewById(R.id.btnGaleria);

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                    Intent.createChooser(intent, "Seleccione una imagen"), SELECT_FILE);


            }
        });

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("vidi", "onClick()");
                Intent intent = new Intent(AjusActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        imageView = (ImageView) findViewById(R.id.imagePerfil);

    }
    @Override
    protected  void onResume(){
        super.onResume();
        Log.d("vidi", "resume");
        //Restaurem Dades
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String nom = prefs.getString("username", "Nom Usuari");
        String img = prefs.getString("imagen", encodedImage );
        byte[] data = Base64.decode(img,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        imageView.setImageBitmap(bitmap);
        /*String imagen = prefs.getString("image",null);
        if (imagen != null){
            byte[] data = Base64.decode(imagen, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

            imageView.setImageBitmap(bitmap);
        }*/

        editText.setText(nom);


    }
    @Override
    protected void  onPause(){
        Log.d("vidi", "pause");
        //DesemDades
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed = prefs.edit();
        String nom = editText.getText().toString();
        ed.putString("username", nom);
       if (encodedImage!=""){
            ed.putString("imagen" , encodedImage);

        }
       /* ed.putString("imagen" , encodedImage);*/
        ed.apply();
        super.onPause();

    }
    @Override
    protected void  onRestart(){
        Log.d("vidi", "restart");
        //DesemDades
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed = prefs.edit();
        String nom = editText.getText().toString();
        ed.putString("username", nom);
        if (encodedImage!=""){
            ed.putString("imagen" , encodedImage);

        }
       /* ed.putString("imagen" , encodedImage);*/
        ed.apply();
        super.onRestart();
    }



    protected void onActivityResult(int requestCode, int resultCode,
                                 Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Uri selectedImage;
        Bitmap bmp=null;
        String filePath = null;
        switch (requestCode) {
            case SELECT_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    String selectedPath = selectedImage.getPath();

                    if (selectedPath != null) {
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(
                                    selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // Transformamos la URI de la imagen a inputStream y este a un Bitmap
                        bmp = BitmapFactory.decodeStream(imageStream);
                        //Log.d("vidi", "setImage");
                        imageView.setImageBitmap(bmp);

                        // Ponemos nuestro bitmap en un ImageView que tengamos en la vista


                    }
                }

                break;
            case SELECT_FILE2:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle extras = imageReturnedIntent.getExtras();
                    bmp = (Bitmap) extras.get("data");
                    imageView.setImageBitmap(bmp);


                }
                break;
        }
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytes = stream.toByteArray();
            String bas364 = Base64.encodeToString(bytes,Base64.DEFAULT);
            encodedImage=bas364;
        Log.d("vidi", "img");


    }
}
