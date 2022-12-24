package model;

import java.nio.file.Path;

abstract public class CeaserCipher {
    Path path;
    int key;

    static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public CeaserCipher(Path path, int key) {
        this.path = path;
        this.key = key;
    }
}
