package model;

public enum CesarCipherCharacters {
    CHARACTERS("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    //Z -> 25 + 1 -> 0 + 1 -> 1
    //25 + 2 -> 1
    //25 + 2 -> 27

    public final String value;

    CesarCipherCharacters(String value) {
        this.value = value;
    }
}
