package Components;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encryptor{
    public static void main(String[] args){

        String password = "Test";

        try{
            // Select the message digest for the hash computation -> SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Generate the random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Passing the salt to the digest for the computation
            md.update(salt);

            StringBuilder sbs = new StringBuilder();
            for (byte b : salt) {
                //Overfører fra byte til hex
                sbs.append(String.format("%02x", b));
            }

            // Getnerate the saled hash
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                //Overfører fra byte til hex
                sb.append(String.format("%02x", b));
            }
            System.out.println(sbs);
            System.out.println(sb);
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
}