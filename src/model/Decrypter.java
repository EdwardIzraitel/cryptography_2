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
        //I want to check keys up until i find the correct key and i want to stop
        //a correct key is found by starting from 0 and moving up
        //and seeing if there is a percentage match to actual words
        //percentage match to actual words -> 2 lines or 1 line
        //and not the whole file

    }

    public void statisticalAnalysis(Path file) {
    }

}
