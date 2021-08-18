package com.mycrolinks.passwdmgr;

import java.io.Serializable;

public class PasswordItem implements Serializable {
    String title;
    String password;
    String timestamp;
    public PasswordItem(String title, String password, String timestamp) {
        this.title = title;
        this.password = password;
        this.timestamp = timestamp;
    }

    public void display() {
        System.out.println("title : "+title);
        System.out.println("password : "+password);
        System.out.println("timestamp : "+timestamp);

    }
}
