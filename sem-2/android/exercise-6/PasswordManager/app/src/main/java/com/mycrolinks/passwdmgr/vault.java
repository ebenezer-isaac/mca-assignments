package com.mycrolinks.passwdmgr;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class vault extends AppCompatActivity {

    GoogleSignInAccount signInAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //System.out.println("hello");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault);
        signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        final ListView list = findViewById(R.id.list);
        try {
            ArrayList<PasswordItem> arrayList = Encryptor.byteArrayToPasswordList(Encryptor.read(signInAccount.getId(), getApplicationContext()));
            for (int i = 0; i < arrayList.size(); i++) {
                PasswordItem temp = arrayList.get(i);
                temp.display();
            }
            CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
            list.setAdapter(customAdapter);
        } catch (IOException | ClassNotFoundException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        Button exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(v -> {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            final View alertBox = layoutInflater.inflate(R.layout.password_item_dialog, null);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Credentials");
            alertDialog.setView(alertBox);
            EditText itemTitle = (EditText) alertBox.findViewById(R.id.alert_title);
            EditText itemPwd = (EditText) alertBox.findViewById(R.id.alert_password);
            alertDialog.setIcon(R.drawable.lock_key);
            alertDialog.setPositiveButton("Save",
                    (dialog, which) -> {
                        Date today = Calendar.getInstance().getTime();
                        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        PasswordItem pwdItem = new PasswordItem(itemTitle.getText().toString(), itemPwd.getText().toString(), df.format(today));
                        PasswordItemWrapper.add(pwdItem, signInAccount.getId(), this);
                        Toast.makeText(this, "Credentials Saved", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, vault.class);
                        this.startActivity(intent);
                    });
            alertDialog.setNeutralButton("Copy",
                    (dialog, which) -> {

                        ClipboardManager clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText(itemTitle.getText().toString(), itemPwd.getText().toString());
                        clipboard.setPrimaryClip(clip);
                        dialog.cancel();
                        Toast.makeText(this, "Password Copied to Clipboard", Toast.LENGTH_SHORT).show();
                    });
            alertDialog.show();

        });
        Button delete = (Button) findViewById(R.id.del_but);
        delete.setOnClickListener(view -> {
            String id = signInAccount.getId();
            File dir = this.getFilesDir();
            File file = new File(dir, id + ".json");
            file.delete();
            file = new File(dir, id + ".bin");
            file.delete();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("root").document(Objects.requireNonNull(signInAccount.getEmail()))
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(vault.this, "Vault successfully deleted!", Toast.LENGTH_SHORT).show();
                        exit.performClick();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(vault.this, "Error deleting vault", Toast.LENGTH_SHORT).show();
                        System.out.println(e.getMessage());
                    });

        });
    }
}