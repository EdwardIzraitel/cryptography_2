package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Decrypter extends CeaserCipher {
    //Set of all possible words from words.txt
    private static Set<String> allowedWords = new HashSet<String>();

    public Decrypter(Path path, int key) {
        super(path, key);
    }

    public void decrypt() {
        writeIntoFile(false);
    }

    private void getAllWords() {
        if (!allowedWords.isEmpty()) return;
        Path wordsPath = Path.of("../words.txt");
        try (BufferedReader br = Files.newBufferedReader(wordsPath)) {
            String word;
            while ((word = br.readLine()) != null) {
                allowedWords.add(word.toLowerCase());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int bruteForce() {
        getAllWords();
        for (int key = 0; key < CHARACTERS.length(); key++) {
            this.key = key;
            StringBuilder sb = createStringBuilderFromFile(false, 500);
            String[] encryptedWords = sb.toString().split(" ");
            int correctWords = 0;
            for (String word : encryptedWords) {
                if (!allowedWords.contains(word.toLowerCase())) continue;
                correctWords++;
            }
            if (((float) correctWords / (float) encryptedWords.length) * 100f > 30f) {
                writeIntoFile(false);
                break;
            }
        }
        return key;
        //loop of some sort to loop from 0 to 25
        //somewhere in that range we are going to find the correct key
        //correct is essentially -> words that we get back that are english words

        //I want to check keys up until i find the correct key and i want to stop
        //a correct key is found by starting from 0 and moving up
        //and seeing if there is a percentage match to actual words
        //percentage match to actual words -> 2 lines or 1 line
        //and not the whole file

    }

    public void statisticalAnalysis(Path file) {
//        getAllWords();
        //Calculate the amount of each character in each file independitly
        //i want to keep track of these characters in a dictionary
        //find the total amount of words
        //Encrypted file
        //

        //Decrypted
        //Calculate the amount of each character in each file independitly
        //i want to keep track of these characters in a dictionary
        //find the total amount of words

        //I want to compare similar percentages and see if i can find the key
        //by getting the difference of character positions
        //
    }

}
