package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Decrypter extends CeaserCipher {
    //Set of all possible words from words.txt
    private static Set<String> allowedWords = new HashSet<String>();

    public Decrypter(Path path, int key) {
        super(path, key);
    }

    public void decrypt() {
        writeIntoFile(path, false);
    }

    private void getAllWords() {
        if (!allowedWords.isEmpty()) return;
        Path wordsPath = Path.of("../words.txt");
        try (BufferedReader br = Files.newBufferedReader(wordsPath)) {
            String word;
            while ((word = br.readLine()) != null) {
                allowedWords.add(word.toLowerCase());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isCorrectlyDecrypted(StringBuilder sb) {
        String[] decryptedWords = sb.toString().toLowerCase().split("\\W+");
        int correctWords = 0;
        for (String word : decryptedWords) {
            if (!allowedWords.contains(word)) continue;
            correctWords++;
        }
        if (((float) correctWords / (float) decryptedWords.length) * 100f > 60f) {
            return true;
        }
        return false;
    }

    public int bruteForce() {
        getAllWords();
        for (int key = 1; key < CHARACTERS.length(); key++) {
            this.key = key;
            StringBuilder sb = createStringBuilderFromFile(path, false, 500);
            if (!isCorrectlyDecrypted(sb)) continue;
            writeIntoFile(path, false);
            return key;
        }
        return -1;
    }

    Map<String, Float> createLetterToCountMap(StringBuilder sb) {
        Map<String, Integer> charCount = new HashMap<>();
        Map<String, Float> result = new HashMap<>();
        int totalCharacters = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (!CHARACTERS.contains(String.valueOf(sb.charAt(i)).toUpperCase())) continue;
            ++totalCharacters;
        }
        for (int i = 0; i < sb.length(); i++) {
            if (!CHARACTERS.contains(String.valueOf(sb.charAt(i)).toUpperCase())) continue;
            if (charCount.containsKey(String.valueOf(sb.charAt(i)).toUpperCase())) {
                charCount.put(String.valueOf(sb.charAt(i)).toUpperCase(), charCount.get(String.valueOf(sb.charAt(i)).toUpperCase()) + 1);
                continue;
            }
            charCount.put(String.valueOf(sb.charAt(i)).toUpperCase(), 1);
        }
        for (String key : charCount.keySet()) {
            result.put(key, (float) charCount.get(key) / totalCharacters);
        }
        return result;
    }

    public int statisticalAnalysis(Path file) {
        getAllWords();
        StringBuilder enSB = createStringBuilderFromFile(path, false, -1);
        StringBuilder deSB = createStringBuilderFromFile(file, false, -1);
        Map<String, Float> enMap = createLetterToCountMap(enSB);
        Map<String, Float> deMap = createLetterToCountMap(deSB);

        for (String dKey : deMap.keySet()) {
            float range = (deMap.get(dKey) / 100f) * 5f;
            for (String eKey : enMap.keySet()) {
                if (dKey.equals(eKey)) continue;
                if (((deMap.get(dKey) + range) > enMap.get(eKey)) && ((deMap.get(dKey) - range) < enMap.get(eKey))) {
                    key = Math.abs(CHARACTERS.indexOf(dKey) - CHARACTERS.indexOf(eKey));
                    StringBuilder attemptSB = createStringBuilderFromFile(path, false, -1);
                    if (!isCorrectlyDecrypted(attemptSB)) continue;
                    writeIntoFile(path, false);
                    return key;
                }
            }
        }
        return -1;
    }

}
