package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Decrypter extends CeaserCipher {

    public Decrypter(Path path, int key) {
        super(path, key);
    }

    public void decrypt() {
        encrypterDecrypterFiles(false);
    }

    public void bruteForce() {
        //I want to check every key from 0-25
        //I want to check that from every key the file entire file has actual words
        // every is an actual word

    }

    public void statisticalAnalysis(Path file) {
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
