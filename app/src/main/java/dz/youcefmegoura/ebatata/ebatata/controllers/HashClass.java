package dz.youcefmegoura.ebatata.ebatata.controllers;

/**
 * Created by Youcef Mégoura on 24/12/2018.
 */

public class HashClass {
    public static String getHash(String txt) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA1");
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            // error action
        }
        return null;
    }

    public static String sha1(String txt) {
        return getHash(txt);
    }
}
