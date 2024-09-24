package com.example.maplibre;

/*
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.Style;

import com.example.maplibre.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    private OkHttpClient client;
    private Gson gson;

    private MapView mapView;

    String apiKey = "599464d94be84c0296fe3edc809c01ca";
    String url = "https://api.geoapify.com/v2/place-details?lat=23.756080876788374&lon=90.36752478086112&features=radius_500,radius_500.atm&apiKey=" + apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize OkHttpClient and Gson instances
        client = new OkHttpClient();
        gson = new Gson();

        Request request = new Request.Builder()
                .url(url)
                .build();

//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e(TAG, "Request Failed", e);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    String responseData = response.body().string();
//                    JsonObject jsonObject = gson.fromJson(responseData, JsonObject.class);
//                    Log.d(TAG, "Response: " + jsonObject.toString());
//                } else {
//                    Log.e(TAG, "Request Failed: " + response.code());
//                }
//            }
//        });

        String mapTilerKey = getMapTilerKey();

        String styleUrl = "https://api.maptiler.com/maps/streets/style.json?key=" + mapTilerKey;

        // Get the MapBox context
//        Mapbox.getInstance(this, null);

        // Create map view
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(map -> {
            // Set the style after mapView was loaded
            map.setStyle(new Style.Builder().fromUri(styleUrl), style -> {
                map.getUiSettings().setAttributionMargins(15, 0, 0, 15);
                // Set the map view center
                map.setCameraPosition(new CameraPosition.Builder()
                        .target(new LatLng(47.127757, 8.579139))
                        .zoom(10.0)
                        .build());
            });
        });
    }


    private String getMapTilerKey() {
        try {
            return getPackageManager().getApplicationInfo(
                    getPackageName(),
                    PackageManager.GET_META_DATA
            ).metaData.getString("com.maptiler.simple.mapTilerKey");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}*/

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.Style;

import android.content.pm.PackageManager;

import com.example.maplibre.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize MapLibre
        String mapTilerKey = "5lE8xgE2lZMxaJgiYGlF";
        validateKey(mapTilerKey);
        Mapbox.getInstance(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Create map view
        mapView = binding.mapView;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(map -> {
            // Set the style after mapView was loaded
            String styleUrl = "https://api.maptiler.com/maps/streets/style.json?key=" + mapTilerKey;
            map.setStyle(new Style.Builder().fromUri(styleUrl), style -> {
                map.getUiSettings().setAttributionMargins(15, 0, 0, 15);
                // Set the map view center
                map.setCameraPosition(new CameraPosition.Builder()
                        .target(new LatLng(23.756080876788374, 90.36752478086112))
                        .zoom(10.0)
                        .build());
            });
        });
    }

    private String getMapTilerKey() {
        try {
            return getPackageManager().getApplicationInfo(
                    getPackageName(),
                    PackageManager.GET_META_DATA
            ).metaData.getString("com.maptiler.simplemap.mapTilerKey");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void validateKey(String mapTilerKey) {
        if (mapTilerKey == null) {
            throw new RuntimeException("Failed to read MapTiler key from AndroidManifest.xml");
        }
        if (mapTilerKey.toLowerCase().equals("placeholder")) {
            throw new RuntimeException("Please enter correct MapTiler key in AndroidManifest.xml");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}

