package Components;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class Encryptor{

    public static void Encryptor(String password){

        String salted;
        String hashed;

        try{
            // Select the message digest for the hash computation -> SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Generate the random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Passing the salt to the digest for the computation
            md.update(salt);

            salted = buildString(salt);

            // Generate the salted hash
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

            //Hashed password
            hashed =  buildString(hashedPassword);

            System.out.println(salted);
            System.out.println(hashed);

        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }

    private static String buildString(byte[] bs){
        StringBuilder sb = new StringBuilder();
        for (byte b : bs) {
            //Convert from byte to hex
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}