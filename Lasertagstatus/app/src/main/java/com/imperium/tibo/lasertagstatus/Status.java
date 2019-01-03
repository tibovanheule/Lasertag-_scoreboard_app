package com.imperium.tibo.lasertagstatus;


import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Status extends AsyncTask<Void, Void, JSONObject> {
    private FullscreenActivity mainActivity;


    Status(FullscreenActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected JSONObject doInBackground(Void... postparam) {
        //de noodzakelijke variabelen

        JSONObject jsonObject;
        try {
            InputStream is;
            URL url = new URL("https://tibovanheule.space/lasershoot/status.php");
            //open de url (dit is niet connecteren) zie google voor meer info
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            //wacht 5 sec voordat er een timeout error wordt gegeven (dit is al een hoge waarde sinds mijn server al vaak reageerd in 35ms)
            urlConnection.setReadTimeout(5000);
            urlConnection.setDoInput(true);
            //ze de methode en disable cache
            urlConnection.setRequestMethod("GET");
            urlConnection.setUseCaches(false);
            //connecteer met de url met de gegeven settings en postdata
            urlConnection.connect();
            //iputstream converteren naar een bufferedinputstream
            is = new BufferedInputStream(urlConnection.getInputStream());
            //buffered reader openen
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
            //open een stringbuilder (dit is beter dan string concatenatie)
            StringBuilder sb = new StringBuilder();
            String line;
            //zolang gelezen lijn van de reader niet null is
            while ((line = reader.readLine()) != null) {
                //append lijn en newline
                sb.append(line);
                sb.append("\n");
            }
            //sluit alles
            is.close();
            reader.close();
            urlConnection.disconnect();
            //json object
            jsonObject = new JSONObject(sb.toString());
        } catch (Exception e) {
            return null;
        }

        return jsonObject;

    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        try {
            // gelukt schrijf message uit naar mainactivity textview response
            mainActivity.setText(jsonObject);
        } catch (Exception e) {
            mainActivity.setText("An error has occurred! check your internet connection!");
        }
        // reset de button
        mainActivity.findViewById(R.id.refresh).setEnabled(true);

    }
}
