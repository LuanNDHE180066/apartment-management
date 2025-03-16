package validation;

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class BadWordFilter {

    public Set<String> getBadWords() {
        return badWords;
    }

    private Set<String> badWords;

   
    public BadWordFilter(String filePath) {
        badWords = loadBadWords(filePath);
    }

    // Load bad words from a text file
    private Set<String> loadBadWords(String filePath) {
        Set<String> words = new HashSet<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                words.add(line.trim().toLowerCase()); // Normalize words to lowercase
            }
            System.out.println("✅ Loaded " + words.size() + " bad words.");
        } catch (IOException e) {
            System.err.println("❌ Error loading bad words file: " + e.getMessage());
        }
        return words;
    }

    // Check if text contains bad words
    public boolean containsBadWord(String text) {
        String lowerText = text.toLowerCase();
        for (String word : badWords) {
            if (lowerText.contains(word)) {
                return true;
            }
        }
        return false;
    }



   
}
