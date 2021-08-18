package com.ebenezer_isaac.eventlistener;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @SuppressLint("SetTextI18n")
    public void display(View v){
        ((TextView)findViewById(R.id.displayName)).setText("Hello "+((EditText)findViewById(R.id.personName)).getText());
    }
}