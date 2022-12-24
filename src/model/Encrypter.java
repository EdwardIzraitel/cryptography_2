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
        String pathName = path.toAbsolutePath().toString();
        int lastIndex = pathName.lastIndexOf(".");
        pathName = pathName.substring(0, lastIndex) + "_encrypted" + pathName.substring(lastIndex);

        try (BufferedReader br = Files.newBufferedReader(path); BufferedWriter bw = Files.newBufferedWriter(Path.of(pathName))) {
            int characterValue;
            char character;
            while ((characterValue = br.read()) != -1) {
                character = (char) characterValue;
                if (!CHARACTERS.contains(String.valueOf(Character.toUpperCase(character)))) {
                    bw.append(character);
                    continue;
                }
                int charPosition = CHARACTERS.indexOf(Character.toUpperCase(character));
                int encryptedPosition = (charPosition + key) % CHARACTERS.length();
                char convertedCharacter = CHARACTERS.charAt(encryptedPosition);

                if (Character.isUpperCase(character)) bw.append(convertedCharacter);
                else bw.append(Character.toLowerCase(convertedCharacter));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
