package controller;

import java.nio.file.Path;

public class Encrypter extends CeaserCipher {

    public Encrypter(Path path, int key) {
        super(path, key);
    }

    public void encrypt() {

    }
}
