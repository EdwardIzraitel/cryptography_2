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

    }

    public void statisticalAnalysis(Path file) {
    }

}
