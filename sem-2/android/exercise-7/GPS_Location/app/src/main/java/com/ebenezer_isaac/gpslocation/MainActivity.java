package com.ebenezer_isaac.gpslocation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_LOCATION_PERMISSION = 1;
    LocationListener locationListener;
    LocationManager locationManager;
    String latitude, longitude;

    @SuppressLint({"MissingPermission", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = location -> {
            latitude = String.valueOf(location.getLatitude());
            longitude = String.valueOf(location.getLongitude());
            Toast.makeText(this, latitude + "\n" + longitude, Toast.LENGTH_SHORT).show();
            System.out.println(latitude + ", " + longitude);
            locationManager.removeUpdates(locationListener);
            TextView lat = (TextView) findViewById(R.id.latitude);
            TextView lon = (TextView) findViewById(R.id.longitude);
            TextView link = (TextView) findViewById(R.id.link);
            lat.setText("Latitude : " + latitude);
            lon.setText("Longitude : " + longitude);
            link.setText("https://www.google.com/maps/@" + latitude + "," + longitude + ",17z");
        };
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(REQUEST_LOCATION_PERMISSION)
    public void requestLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
        } else {
            EasyPermissions.requestPermissions(this, "Please grant the location permission", REQUEST_LOCATION_PERMISSION, perms);
        }
    }

    @SuppressLint("MissingPermission")
    public void updateLocation(View view) {
        Toast.makeText(this, "Fetching Location", Toast.LENGTH_SHORT).show();
        requestLocationPermission();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }
}