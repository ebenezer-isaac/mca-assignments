package com.mycrolinks.passwdmgr;

//import android.Manifest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.firebase.auth.FirebaseAuth;

//import android.graphics.Color;
//import android.location.Location;
//import android.view.View;
//import androidx.annotation.NonNull;

//import pub.devrel.easypermissions.AfterPermissionGranted;
//import pub.devrel.easypermissions.EasyPermissions;

@SuppressWarnings("deprecation")
public class vault_pin extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    //private final int REQUEST_LOCATION_PERMISSION = 1;
    GoogleSignInAccount signInAccount;
    //String[] loc;
    String password_db;
    Button unlock, logout, delete;
    CheckBox checkbox;
    GoogleApiClient googleApiClient;
    String SITE_KEY = "6LcbWZgbAAAAAGj9z9VVweX8s5htypF-5ykAzM5V";

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault_pin);
        unlock = findViewById(R.id.signin);
        logout = findViewById(R.id.log_but);
        delete = findViewById(R.id.del_but);
        checkbox = findViewById(R.id.checkbox);
        signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        password_db = getIntent().getStringExtra("data");

        googleApiClient = new GoogleApiClient.Builder(this).addApi(SafetyNet.API).addConnectionCallbacks(vault_pin.this).build();
        googleApiClient.connect();

        checkbox.setOnClickListener(v -> {
            if (checkbox.isChecked()) {
                SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, SITE_KEY).setResultCallback(recaptchaTokenResult -> {
                    Status status = recaptchaTokenResult.getStatus();
                    if (status.isSuccess()) {
                        Toast.makeText(vault_pin.this, "Captcha Verified Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        checkbox.setSelected(false);
                    }
                });
            }
        });

        logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        unlock.setOnClickListener(view -> {
            EditText passwordEditText = findViewById(R.id.password_editText);
            String password = passwordEditText.getText().toString();
            if(checkbox.isChecked()){
                if (Encryptor.hash_wrapper(password).equals(password_db)) {
                    Intent intent = new Intent(getApplicationContext(), vault.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Verify ReCAPTCHA", Toast.LENGTH_SHORT).show();
            }
        });

        //requestLocationPermission();
        //LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,  this);

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        // Forward results to EasyPermissions
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }
//
//    @AfterPermissionGranted(REQUEST_LOCATION_PERMISSION)
//    public void requestLocationPermission() {
//        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
//        if (EasyPermissions.hasPermissions(this, perms)) {
//            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
//        } else {
//            EasyPermissions.requestPermissions(this, "Please grant the location permission", REQUEST_LOCATION_PERMISSION, perms);
//        }
//    }

//    @Override
//    public void onLocationChanged(@NonNull Location location) {
//        loc[0] = String.valueOf(location.getLatitude());
//        loc[1] = String.valueOf(location.getLongitude());
//        System.out.println(loc[0]);
//        System.out.println(loc[1]);
//
//    }
}