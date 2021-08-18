package com.example.studentdb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button refresh;
    int student_id_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);
        refresh = findViewById(R.id.refresh);
        refresh.performClick();
    }

    @SuppressLint("SetTextI18n")
    public void populateData(View v) {
        TableLayout table = findViewById(R.id.display_table);
        table.removeAllViews();
        System.out.println("cleared");
        ArrayList student_details = db.getAllStudents();
        if (student_details.size() > 0) {
            for (int i = 0; i < student_details.size(); i++) {
                ArrayList student = (ArrayList) student_details.get(i);
                for (int j = 1; j < student.size(); j++) {
                    TableRow row = new TableRow(this);
                    TextView item = new TextView(this);
                    if (j == student.size() - 1) {
                        if (student.get(6).toString().equals("0")) {
                            item.setText("Day Scholar");
                        } else {
                            item.setText("Hosteller");
                        }
                    } else {
                        item.setText(student.get(j).toString());
                    }
                    item.setTextSize(20);
                    item.setPadding(0, 10, 0, 10);
                    item.setGravity(Gravity.CENTER);
                    row.setGravity(Gravity.CENTER);
                    row.addView(item);
                    table.addView(row);
                }
                TableRow row = new TableRow(this);
                Button del = new Button(this);
                del.setText("Delete");
                del.setTag(student.get(0));
                del.setGravity(1);
                row.setGravity(Gravity.CENTER);

                del.setId(i);
                del.setOnClickListener(v1 -> {
                    db.deleteStudent(Integer.parseInt(v1.getTag().toString()));
                    refresh.performClick();
                });
                row.addView(del);
                Button upd = new Button(this);
                upd.setText("Update");
                upd.setTag(student.get(0));
                upd.setGravity(1);
                upd.setId(i);
                upd.setOnClickListener(v1 -> {
                    student_id_update = Integer.parseInt(v1.getTag().toString());
                    setContentView(R.layout.insert_form);
                    populateFormData();
                });
                row.addView(upd);
                table.addView(row);
                TableRow row_separate = new TableRow(this);
                TextView item = new TextView(this);
                item.setText("x---------x");
                item.setGravity(Gravity.CENTER);
                item.setTextSize(20);
                item.setGravity(Gravity.CENTER);
                row_separate.setGravity(Gravity.CENTER);
                row_separate.addView(item);
                table.addView(row_separate);
            }
        } else {
            Toast.makeText(this, "Empty Database", Toast.LENGTH_SHORT).show();

        }

    }

    public void populateFormData() {
        ArrayList student_details = db.getStudent(student_id_update);
        ((EditText) findViewById(R.id.name)).setText(student_details.get(1).toString());
        ((EditText) findViewById(R.id.email)).setText(student_details.get(2).toString());
        ((EditText) findViewById(R.id.city)).setText(student_details.get(3).toString());
        ((EditText) findViewById(R.id.phone_no)).setText(student_details.get(4).toString());
        ((EditText) findViewById(R.id.cgpa)).setText(student_details.get(5).toString());
        ((Spinner) findViewById(R.id.hostel_stat)).setSelection(Integer.parseInt(student_details.get(6).toString())==0?1:0);
    }

    public void insertPage(View v) {
        setContentView(R.layout.insert_form);
        student_id_update=-1;
    }

    public void back(View v) {
        setContentView(R.layout.activity_main);
        refresh.performClick();
    }

    public void insertDetails(View v) {
        if (student_id_update == -1) {
            String result = db.insertStudent(((EditText) findViewById(R.id.name)).getText().toString() + "",
                    ((EditText) findViewById(R.id.email)).getText().toString() + "",
                    ((EditText) findViewById(R.id.city)).getText().toString() + "",
                    ((EditText) findViewById(R.id.phone_no)).getText().toString() + "",
                    ((EditText) findViewById(R.id.cgpa)).getText().toString() + "",
                    ((Spinner) findViewById(R.id.hostel_stat)).getSelectedItemPosition() == 0);
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            if (result.equals("Data inserted successfully")) {
                setContentView(R.layout.activity_main);
                refresh.performClick();
            }
        } else {
            String result = db.updateStudent(student_id_update, ((EditText) findViewById(R.id.name)).getText().toString() + "",
                    ((EditText) findViewById(R.id.email)).getText().toString() + "",
                    ((EditText) findViewById(R.id.city)).getText().toString() + "",
                    ((EditText) findViewById(R.id.phone_no)).getText().toString() + "",
                    ((EditText) findViewById(R.id.cgpa)).getText().toString() + "",
                    ((Spinner) findViewById(R.id.hostel_stat)).getSelectedItemPosition() == 0);
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            if (result.equals("Data inserted successfully")) {
                setContentView(R.layout.activity_main);
                refresh.performClick();
            }
            student_id_update=-1;
        }
    }
}