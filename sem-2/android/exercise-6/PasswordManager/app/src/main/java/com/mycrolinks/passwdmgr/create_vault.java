package com.mycrolinks.passwdmgr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.firestore.FirebaseFirestore;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class create_vault extends AppCompatActivity {
    EditText pass_new, pass_conf;
    Button create;
    FirebaseFirestore db;
    GoogleSignInAccount signInAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vault);
        pass_new = findViewById(R.id.pass_new);
        pass_conf = findViewById(R.id.pass_conf);
        create = findViewById(R.id.create);
        create.setOnClickListener(view -> {
            if (pass_new.getText().toString().equals(pass_conf.getText().toString())) {
                String password = Encryptor.hash_wrapper(pass_new.getText().toString());
                Map<String, Object> passwd = new HashMap<>();
                passwd.put("password", password);
                signInAccount = GoogleSignIn.getLastSignedInAccount(this);
                db = FirebaseFirestore.getInstance();

                db.collection("root").document(Objects.requireNonNull(signInAccount.getEmail())).set(passwd)
                        .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(this, "Vault created successfully", Toast.LENGTH_SHORT).show();
                                    try {
                                        ArrayList<PasswordItem> arrayList = new ArrayList<>();
                                        Date today = Calendar.getInstance().getTime();
                                        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                        PasswordItem dummy= new PasswordItem("Dummy", "example password", df.format(today));
                                        arrayList.add(dummy);
                                        Encryptor.save(Objects.requireNonNull(signInAccount.getId()), Encryptor.passwordListToByteArray(arrayList), getApplicationContext());
                                        Intent intent = new Intent(getApplicationContext(), vault.class);
                                        startActivity(intent);
                                    } catch (GeneralSecurityException | IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                        )
                        .addOnFailureListener(e -> System.out.println("Error writing document" + e.getMessage()));


            } else {
                Toast.makeText(this, "Passwords Didn't Match", Toast.LENGTH_SHORT).show();
            }
        });

    }
}