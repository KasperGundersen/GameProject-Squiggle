package Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.util.ArrayList;

public class Words {

    public static void main(String[] args) {
        String wordlist = "C:\\Users\\bruker\\Documents\\squiggle\\src\\Database\\wordlist.txt";
        try {
            FileReader readConnection = new FileReader(wordlist);
            BufferedReader readWordlist = new BufferedReader(readConnection);
            String listRead;
            String[] words;
            while((listRead = readWordlist.readLine())!= null){
                words = listRead.split("-");
                for(String s : words) {
                    DBConnection.insertIntoDB(s.trim());
                }
            }
            readWordlist.close();
            readConnection.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("IOException");
        }
    }
}
