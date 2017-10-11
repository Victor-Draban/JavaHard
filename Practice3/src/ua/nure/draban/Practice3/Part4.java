package ua.nure.draban.Practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {
    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        try {
            digest.update(input.getBytes("Cp1251"));
        } catch (Exception e) {
            System.out.println(e);
        }
        byte[] hash = digest.digest();
        for (byte iter : hash) {
            sb.append(String.format("%02X", iter & 0xff));
        }
        return sb.toString();
    }
}
