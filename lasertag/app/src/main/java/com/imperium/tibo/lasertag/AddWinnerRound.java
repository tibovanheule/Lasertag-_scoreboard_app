package com.imperium.tibo.lasertag;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AddWinnerRound extends AsyncTask<Void, Void, JSONObject> {
    private MainActivity mainActivity;
    private String postData;

    AddWinnerRound(MainActivity mainActivity, String postData) {
        this.mainActivity = mainActivity;
        this.postData = postData;
    }


    /**
     * stuurt de postdata naar de url.
     */
    @Override
    protected JSONObject doInBackground(Void... postparam) {
        //de noodzakelijke variabelen

        JSONObject jsonObject;
        try {
            InputStream is;
            URL url = new URL("https://tibovanheule.space/lasershoot/blablaupdatelol.php");
            //open de url (dit is niet connecteren) zie google voor meer info
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            //wacht 5 sec voordat er een timeout error wordt gegeven (dit is al een hoge waarde sinds mijn server al vaak reageerd in 35ms)
            urlConnection.setReadTimeout(5000);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            //ze de methode en disable cache
            urlConnection.setRequestMethod("POST");
            urlConnection.setUseCaches(false);
            //header content type zetten
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // open een printwtriter van de output stream
            PrintWriter out = new PrintWriter(urlConnection.getOutputStream());
            //print de postdata (url parameters) ernaar uit
            out.print(postData);
            out.close();
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
            mainActivity.setText(jsonObject.getString("message"));
        } catch (Exception e) {
            mainActivity.setText("An error has occurred!");
        }
        // reset de button
        mainActivity.findViewById(R.id.send).setEnabled(true);

    }
}
