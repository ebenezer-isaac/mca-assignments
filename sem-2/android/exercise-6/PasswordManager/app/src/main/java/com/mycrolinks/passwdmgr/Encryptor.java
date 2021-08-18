package com.mycrolinks.passwdmgr;

import android.content.Context;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetReader;
import com.google.crypto.tink.JsonKeysetWriter;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class Encryptor {

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    private static String hash(String password, String salt) {
        MessageDigest digest;
        String hash;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.update((password + salt).getBytes());
            hash = bytesToHexString(digest.digest());
            return hash;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public static String hash_wrapper(String password) {
        String hash = password;
        for (int x = 0; x < password.length(); x++) {
            hash = hash(hash, password.charAt(x) + "");
        }
        return hash;
    }


    public static byte[] read(String id, Context applicationContext) throws GeneralSecurityException, IOException {
        AeadConfig.register();
        KeysetHandle keyHandle = CleartextKeysetHandle.read(JsonKeysetReader.withInputStream(applicationContext.openFileInput(id + ".json")));
        Aead aead = keyHandle.getPrimitive(Aead.class);
        byte[] ciphertext = new byte[0];
        try {
            FileInputStream fis=  applicationContext.openFileInput(id + ".bin");
            ObjectInputStream in = new ObjectInputStream(fis);
            ciphertext = (byte[]) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return aead.decrypt(ciphertext, id.getBytes());
    }

    public static void save(String id, byte[] data, Context applicationContext) throws GeneralSecurityException, IOException {
        AeadConfig.register();
        KeysetHandle keyHandler;
        keyHandler = KeysetHandle.generateNew(KeyTemplates.get("AES128_GCM"));
        File dir = applicationContext.getFilesDir();
        File file = new File(dir, id + ".json");
        System.out.println(file.delete());
        file = new File(dir, id + ".bin");
        System.out.println(file.delete());
        CleartextKeysetHandle.write(keyHandler, JsonKeysetWriter.withOutputStream(applicationContext.openFileOutput(id + ".json", Context.MODE_PRIVATE)));
        Aead aead = keyHandler.getPrimitive(Aead.class);

        byte[] ciphertext = aead.encrypt(data, id.getBytes());
        try {
            FileOutputStream fileOut = applicationContext.openFileOutput(id + ".bin", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(ciphertext);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static byte[] passwordListToByteArray(ArrayList<PasswordItem> data) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(data);
        return byteOut.toByteArray();
    }

    public static ArrayList<PasswordItem> byteArrayToPasswordList(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
        ObjectInputStream in = new ObjectInputStream(byteIn);
        return  (ArrayList<PasswordItem>) in.readObject();
    }
}
