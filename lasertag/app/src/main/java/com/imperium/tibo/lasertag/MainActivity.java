package com.imperium.tibo.lasertag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * status van de connectie (geretuneerde json) weergeven
     */
    public void setText(String text) {
        TextView response = findViewById(R.id.response);
        response.setText(text);
    }

    /**
     * wanneer button wordt geklikt, button disablen en id,lat,lon opvragen
     * dan de postdate string opstellen. dit zijn in feite de url parameters die in de header worden meegegeven.(in de header niet url).
     * Vervolgens maak een AsyncTask klasse (noodzakelijk voor internet connecties (moet altijd in aparte thread van android)).
     * deze wordt dan uitgevoerd.
     */
    public void updateCoordinaat(View button) {
        try {
            button.setEnabled(false);
            EditText id = findViewById(R.id.id);
            String postData = "id=" + URLEncoder.encode(id.getText().toString(), "UTF-8");
            AddWinnerRound addWinnerRound = new AddWinnerRound(this, postData);
            addWinnerRound.execute();
        } catch (Exception e) {
            Log.d("TAG", "MSG", e);
        }
    }

}
