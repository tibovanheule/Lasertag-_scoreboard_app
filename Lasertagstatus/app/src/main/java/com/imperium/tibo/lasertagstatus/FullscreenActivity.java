package com.imperium.tibo.lasertagstatus;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;


public class FullscreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        TextView tx = (TextView) findViewById(R.id.status);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "font.otf");
        tx.setTypeface(custom_font);
        tx.setGravity(Gravity.CENTER);
        Status status = new Status(this);
        status.execute();
        // reset de button
        findViewById(R.id.refresh).setEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public void setText(String string) {
        TextView editText = findViewById(R.id.status);
        editText.setText(string);
    }

    public void setText(JSONObject string) {
        try {
            JSONArray array = string.getJSONArray("verdiepen");
            TextView editText = findViewById(R.id.status);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < array.length(); i++) {
                JSONObject row = array.getJSONObject(i);
                stringBuilder.append(row.getString("message"));
                stringBuilder.append("\n\n");
            }
            editText.setText(stringBuilder.toString());
        } catch (Exception e) {
            setText("An error has occured.");
        }
    }

    public void refresh(View view) {
        view.setEnabled(false);
        setText("Refreshing");
        Status status = new Status(this);
        status.execute();
    }
}
