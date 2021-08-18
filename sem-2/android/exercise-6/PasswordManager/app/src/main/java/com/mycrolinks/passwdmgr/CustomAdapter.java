package com.mycrolinks.passwdmgr;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class CustomAdapter implements ListAdapter {
    ArrayList<PasswordItem> arrayList;
    Context context;
    GoogleSignInAccount signInAccount;
    DateFormat df;
    @SuppressLint("SimpleDateFormat")
    public CustomAdapter(Context context, ArrayList<PasswordItem> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
        signInAccount = GoogleSignIn.getLastSignedInAccount(context);
        df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PasswordItem pwdItem=arrayList.get(position);
        if(convertView==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.list_row, null);
            TextView title=convertView.findViewById(R.id.list_title);
            title.setText(pwdItem.title);
            convertView.setOnClickListener(v -> {
                final View alertBox = layoutInflater.inflate(R.layout.password_item_dialog, null);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Credentials");
                alertDialog.setView(alertBox);
                EditText itemTitle = (EditText) alertBox.findViewById(R.id.alert_title);
                itemTitle.setText(pwdItem.title);
                EditText itemPwd = (EditText) alertBox.findViewById(R.id.alert_password);
                itemPwd.setText(pwdItem.password);
                TextView itemTime = (TextView) alertBox.findViewById(R.id.alert_time);
                itemTime.setText("Last Modified : " + pwdItem.timestamp);
                alertDialog.setIcon(R.drawable.lock_key);
                alertDialog.setPositiveButton("Save",
                        (dialog, which) -> {
                            Date today = Calendar.getInstance().getTime();
                            PasswordItem new_pwdItem = new PasswordItem(itemTitle.getText().toString(),itemPwd.getText().toString(),df.format(today));
                            PasswordItemWrapper.update(pwdItem, new_pwdItem, signInAccount.getId(), context);
                            Toast.makeText(context, "Credentials Saved", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, vault.class);
                            context.startActivity(intent);
                        });
                alertDialog.setNeutralButton("Copy",
                        (dialog, which) -> {
                            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText(pwdItem.title, pwdItem.password);
                            clipboard.setPrimaryClip(clip);
                            dialog.cancel();
                            Toast.makeText(context, "Password Copied to Clipboard", Toast.LENGTH_SHORT).show();
                        });
                alertDialog.setNegativeButton("Delete",
                        (dialog, which) -> {
                            PasswordItemWrapper.delete(pwdItem, signInAccount.getId(), context);
                            Toast.makeText(context, "Credentials Deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, vault.class);
                            context.startActivity(intent);
                        });
                alertDialog.show();
            });

        }
        return convertView;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}