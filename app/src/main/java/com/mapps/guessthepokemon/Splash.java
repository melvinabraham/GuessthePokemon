package com.mapps.guessthepokemon;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.spec.ECField;
import java.util.concurrent.ExecutionException;

public class Splash extends AppCompatActivity {

    String passString = "";
    int rLength = 0, lLength = 0;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        DownloadTasker downloadTask = new DownloadTasker();



        downloadTask.execute("http://www.giantbomb.com/profile/wakka/lists/the-150-original-pokemon/59579/");



    }


    public class DownloadTasker extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {


            URL url;
            HttpURLConnection urlConnection = null;


            try {

                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();


                }

                return result;

            } catch (Exception e) {

                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(String result) {




        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.putExtra("Website", result);
        startActivity(i);
        finish();


        }



    }

}










