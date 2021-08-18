package com.example.studentdb;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "students.db";
    public static final String TABLE_NAME = "student_details";
    public static final String STUDENT_ID = "id";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_EMAIL = "email";
    public static final String STUDENT_CGPA = "cgpa";
    public static final String STUDENT_CITY = "city";
    public static final String STUDENT_PHONE = "phone";
    public static final String STUDENT_HOSTEL = "hostel";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE `student_details` (\n" +
                        "  `id` INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "  `name` TEXT(100) NOT NULL,\n" +
                        "  `email` TEXT(50) NOT NULL UNIQUE,\n" +
                        "  `city` TEXT(20) NOT NULL,\n" +
                        "  `phone` TEXT(10) NOT NULL UNIQUE,\n" +
                        "  `cgpa` TEXT(5) NOT NULL,\n" +
                        "  `hostel` BOOLEAN(1) NOT NULL\n" +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String insertStudent(String name, String email, String city, String phone, String cgpa, boolean hostel) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (name.length() > 0 && email.length() > 0 && city.length() > 0 && phone.length() > 0 && cgpa.length() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(STUDENT_NAME, name);
            contentValues.put(STUDENT_EMAIL, email);
            contentValues.put(STUDENT_CITY, city);
            contentValues.put(STUDENT_PHONE, phone);
            contentValues.put(STUDENT_CGPA, cgpa);
            contentValues.put(STUDENT_HOSTEL, hostel);
            try {
                db.insertOrThrow(TABLE_NAME, null, contentValues);
                return "Data inserted successfully";
            } catch (SQLException e) {
                return e.getMessage();
            }
        } else {
            return "All fields are required";
        }
    }

    public String updateStudent(Integer id, String name, String email, String city, String phone, String cgpa, boolean hostel) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (name.length() > 0 && email.length() > 0 && city.length() > 0 && phone.length() > 0 && cgpa.length() > 0) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME, name);
        contentValues.put(STUDENT_EMAIL, email);
        contentValues.put(STUDENT_CITY, city);
        contentValues.put(STUDENT_PHONE, phone);
        contentValues.put(STUDENT_CGPA, cgpa);
        contentValues.put(STUDENT_HOSTEL, hostel);
            try {
                db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
                return "Data inserted successfully";
            } catch (SQLException e) {
                return e.getMessage();
            }
        } else {
            return "All fields are required";
        }
    }

    public void deleteStudent(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList getAllStudents() {
        ArrayList<ArrayList<String>> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(res.getString(res.getColumnIndex(STUDENT_ID)));
            temp.add(res.getString(res.getColumnIndex(STUDENT_NAME)));
            temp.add(res.getString(res.getColumnIndex(STUDENT_EMAIL)));
            temp.add(res.getString(res.getColumnIndex(STUDENT_CITY)));
            temp.add(res.getString(res.getColumnIndex(STUDENT_CGPA)));
            temp.add(res.getString(res.getColumnIndex(STUDENT_PHONE)));
            temp.add(res.getString(res.getColumnIndex(STUDENT_HOSTEL)));
            res.moveToNext();
            array_list.add(temp);
        }
        res.close();
        return array_list;
    }

    public ArrayList getStudent(int id) {
        ArrayList<String> student_detail = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+" where id = "+id, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            student_detail.add(res.getString(res.getColumnIndex(STUDENT_ID)));
            student_detail.add(res.getString(res.getColumnIndex(STUDENT_NAME)));
            student_detail.add(res.getString(res.getColumnIndex(STUDENT_EMAIL)));
            student_detail.add(res.getString(res.getColumnIndex(STUDENT_CITY)));
            student_detail.add(res.getString(res.getColumnIndex(STUDENT_CGPA)));
            student_detail.add(res.getString(res.getColumnIndex(STUDENT_PHONE)));
            student_detail.add(res.getString(res.getColumnIndex(STUDENT_HOSTEL)));
            res.moveToNext();
        }
        res.close();
        return student_detail;
    }
}