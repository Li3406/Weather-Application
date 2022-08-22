package com.example.fit2081weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findWeather(View view) {
        // when user click the search button
        makeRequest();
    }

    private void makeRequest() {
        TextView temp_c = (TextView) findViewById(R.id.resultTemp);
        TextView precip_mm = (TextView) findViewById(R.id.resultPrecipitation);
        TextView humid = (TextView) findViewById(R.id.resultHumidity);
        TextView wind_kph = (TextView) findViewById(R.id.resultWind);

        EditText locationInput = findViewById(R.id.editLocation);
        String location = locationInput.getText().toString();

        // RequestQueue transport requests and handle caching
        RequestQueue requestQueue = Volley.newRequestQueue(this);    // instantiate

        // our web service address
        String url = "https://api.weatherapi.com/v1/current.json?key=cde75afa40ce4179b2781925223003&q=" + location;

        // a request to retrieve a JsonObject response body at a given URL
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject listItems = response.getJSONObject("current");
                            Iterator<?> keys = listItems.keys();

                            String temperature = listItems.getString("temp_c");
                            String precipitation = listItems.getString("precip_mm");
                            String humidity = listItems.getString("humidity");
                            String wind = listItems.getString("wind_kph");

                            temp_c.setText(temperature);
                            precip_mm.setText(precipitation);
                            humid.setText(humidity);
                            wind_kph.setText(wind);

                        }
                        catch (Exception e) {
                            Log.d("weather condition", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("weather condition", error.getMessage());
                    }
                }
        );


        // due to long response time, we need to add a long delay time
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest);

    }
}
