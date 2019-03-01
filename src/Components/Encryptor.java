package Components;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Encryptor {
    public static String Encryptor(String password, String saltest) {
        try {
            // Select the message digest for the hash computation -> SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Generate the random salt
            byte[] salt;
            if(saltest == null) {
                SecureRandom random = new SecureRandom();
                salt = new byte[16];
                random.nextBytes(salt);
            }else{
                salt = buildBytes(saltest);
            }
            // Passing the salt to the digest for the computation
            md.update(salt);
            System.out.println(Arrays.toString(salt));
            String salted = buildHexString(salt);
            // Generate the salted hash
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            //Hashed password
            String hashed = buildHexString(hashedPassword);
            return hashed + "|" + salted;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String buildHexString(byte[] bs) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bs) {
            //Convert from byte to hex
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    private static byte[] buildBytes(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < s.length(); i+=2) {
            int v = Integer.parseInt(s.substring(i, i + 2), 16);
            b[i/2] = (byte) v;
        }
        return b;
    }
    //Splitt string from Encryptior to Hash using the splitter
    public static String getHash(String s){
        int index = s.indexOf('|');
        if(index < 0){
            return null;
        }
        return s.substring(0, index);
    }
    //Splitt string from Encryptior to Salt using the splitter
    public static String getSalt(String s){
        int index = s.indexOf('|');
        if(index < 0){
            return null;
        }
        return s.substring(index+1, s.length());
    }
}
