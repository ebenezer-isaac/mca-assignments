package com.mycrolinks.passwdmgr;

import android.content.Context;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

public class PasswordItemWrapper {

    public static void delete(PasswordItem obj,String id, Context context){
        try {
            ArrayList<PasswordItem> arrayList = Encryptor.byteArrayToPasswordList(Encryptor.read(id, context));
            for(int index =0; index<arrayList.size();index++){
                PasswordItem dummy = arrayList.get(index);
                if(dummy.title.equals(obj.title)){
                    arrayList.remove(index);
                    break;
                }
            }
            Encryptor.save(id, Encryptor.passwordListToByteArray(arrayList),context);
        } catch (IOException | ClassNotFoundException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public static void add(PasswordItem obj,String id, Context context){
        System.out.println("add");
        try {
            ArrayList<PasswordItem> arrayList = Encryptor.byteArrayToPasswordList(Encryptor.read(id, context));
            System.out.println(arrayList.size());
            arrayList.add(obj);
            System.out.println(arrayList.size());
            Encryptor.save(id, Encryptor.passwordListToByteArray(arrayList),context);
        } catch (IOException | ClassNotFoundException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        System.out.println("add over");
    }

    public static void update(PasswordItem old_obj, PasswordItem new_obj,String id, Context context){
        try {
            ArrayList<PasswordItem> arrayList = Encryptor.byteArrayToPasswordList(Encryptor.read(id, context));
            for(int index =0; index<arrayList.size();index++){
                PasswordItem dummy = arrayList.get(index);
                if(dummy.title.equals(old_obj.title)){
                    arrayList.remove(index);
                    arrayList.add(index,new_obj);
                    break;
                }
            }
            Encryptor.save(id, Encryptor.passwordListToByteArray(arrayList),context);
        } catch (IOException | ClassNotFoundException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
