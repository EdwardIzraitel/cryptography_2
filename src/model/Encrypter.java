package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Encrypter extends CeaserCipher {

    public Encrypter(Path path, int key) {
        super(path, key);
    }

    public void encrypt() {
        writeIntoFile(path, true);
    }
}
