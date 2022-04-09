package ro.tuc.ds2020.util;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

    private Hasher(){};

    public static String MD5Hash( String string ){
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(string.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        }

        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String SHA256Hash( String string ){

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] messageDigest = md.digest(string.getBytes( StandardCharsets.UTF_8 ));

            BigInteger no = new BigInteger(1 , messageDigest);

            StringBuilder hashText = new StringBuilder( no.toString(16) );

            // SHA is 32 bytes long, apply padding
            while( hashText.length() < 32 ){
                hashText.insert(0, "0");
            }

            return hashText.toString();

        }catch ( NoSuchAlgorithmException e ){
            e.printStackTrace();
            return null;
        }
    }

}

