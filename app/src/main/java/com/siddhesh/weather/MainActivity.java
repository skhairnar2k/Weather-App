package com.siddhesh.weather;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "38fea22087b523d3c574ba6ba9f7c0f1";
    private static final String API_ENDPOINT = "https://api.openweathermap.org/data/2.5/weather";

    private EditText locationEditText;
    private TextView weatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationEditText = findViewById(R.id.locationEditText);
        weatherTextView = findViewById(R.id.weatherTextView);

        Button fetchWeatherButton = findViewById(R.id.fetchWeatherButton);
        fetchWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = locationEditText.getText().toString();
                if (!location.isEmpty()) {
                    fetchWeatherData(location);
                }
            }
        });
    }

    private void fetchWeatherData(String location) {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = API_ENDPOINT + "?q=" + location + "&appid=" + API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Parse the JSON response using Gson
                        Gson gson = new Gson();
                        WeatherData weatherData = gson.fromJson(response.toString(), WeatherData.class);

                        // Update UI with weather information
                        updateUI(weatherData);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        weatherTextView.setText("Error fetching weather data");
                    }
                });

        queue.add(request);
    }

    private void updateUI(WeatherData weatherData) {
        // Update UI with weather information
        String weatherInfo = "Temperature: " + weatherData.getMain().getTemp() + "°C\n"
                + "Description: " + weatherData.getWeather()[0].getDescription();
        weatherTextView.setText(weatherInfo);
    }
}
