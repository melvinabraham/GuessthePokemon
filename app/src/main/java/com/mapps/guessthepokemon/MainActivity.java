package com.mapps.guessthepokemon;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.AsynchronousCloseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    ArrayList <String> pokeURL = new ArrayList<String >();

    ArrayList <String> pokeName = new ArrayList<String >();

    public class DownloadTask extends AsyncTask< String,Void,String >  {
        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;


            try {

                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while(data!= -1)    {

                   char current = (char) data;

                    result += current;

                    data = reader.read();


                }

                return result;

            }
            catch (Exception e) {

                e.printStackTrace();
            }



            return null;
        }


        @Override
        protected void onPostExecute(String result) {

            //System.out.println(result);
            String [] splitResult = result.split("<div class=\"navigation js-simple-paginator\">");
            //System.out.println(splitResult[1]);
           Pattern p = Pattern.compile("img src=\"(.*?)\"");
           Matcher m = p.matcher(splitResult[1]);
            while (m.find())    {

                //pokeURL.add(m.group(1));
                System.out.println(m.group(1));
            }

        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask downloadTask = new DownloadTask();
        String result = null;
        try {


            //result = downloadTask.execute("https://www.google.co.in").get();

           // Log.i("URL : ",result);

            downloadTask.execute("http://www.giantbomb.com/profile/wakka/lists/the-150-original-pokemon/59579/");
            Log.i("","!!!!!!!!!!!!!!!!!!!!!!!!     DONE         !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        }

        catch (Exception e){

            e.printStackTrace();
            Log.i("Not","Working");

        }


    }
}
