package controller;

import java.nio.file.Path;

abstract public class CeaserCipher {
    private Path path;
    private int key;

    public CeaserCipher(Path path, int key) {
        this.path = path;
        this.key = key;
    }
}
