package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

abstract public class CeaserCipher {
    Path path;
    int key;

    static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public CeaserCipher(Path path, int key) {
        this.path = path;
        this.key = key;
    }

    String resultPathName(boolean encrypt) {
        String endFileName = encrypt ? "_encrypted" : "_decrypted";
        String pathName = path.toAbsolutePath().toString();
        int lastIndex = pathName.lastIndexOf(".");
        return pathName.substring(0, lastIndex) + endFileName + pathName.substring(lastIndex);
    }

    void writeIntoFile(boolean encrypt) {
        StringBuilder decryptedText = createStringBuilderFromFile(encrypt, -1);
        try (BufferedWriter bw = Files.newBufferedWriter(Path.of(resultPathName(encrypt)))) {
            bw.append(decryptedText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    StringBuilder createStringBuilderFromFile(boolean encrypt, int limit) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            int characterValue;
            char character;
            CharPosition getNewCharPosition;
            if (encrypt) getNewCharPosition = (charPosition, key) -> (charPosition + key) % CHARACTERS.length();
            else
                getNewCharPosition = (charPosition, key) -> (charPosition - (key % CHARACTERS.length()) + CHARACTERS.length()) % CHARACTERS.length();
            while ((characterValue = br.read()) != -1 && (limit == -1 || sb.length() < limit)) {
                character = (char) characterValue;
                if (!CHARACTERS.contains(String.valueOf(Character.toUpperCase(character)))) {
                    sb.append(character);
                    continue;
                }
                int charPosition = CHARACTERS.indexOf(Character.toUpperCase(character));
                int encryptedPosition = getNewCharPosition.getCharPosition(charPosition, key);
                char convertedCharacter = CHARACTERS.charAt(encryptedPosition);

                if (Character.isUpperCase(character)) sb.append(convertedCharacter);
                else sb.append(Character.toLowerCase(convertedCharacter));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sb;
    }
}
