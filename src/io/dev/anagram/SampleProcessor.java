package io.dev.anagram;

import java.io.*;
import java.util.*;

public class SampleProcessor {

    public static void init() {
        String inputFile = "input/sample.txt";
        String outputFile = "output/sample.txt";

        Map<String, List<String>> mappedAnagrams = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String word;
            while ((word = reader.readLine()) != null) {
                word = word.trim().toLowerCase(Locale.ROOT);
                if (word.isEmpty()) continue;

                int[] freq = new int[26];
                for (char c : word.toCharArray()) {
                    if (c >= 'a' && c <= 'z') {
                        freq[c - 'a']++;
                    }
                }

                StringBuilder keyBuilder = new StringBuilder();
                for (int f : freq) {
                    keyBuilder.append(f).append(',');
                }
                String key = keyBuilder.toString();
                mappedAnagrams.computeIfAbsent(key, string -> new ArrayList<>()).add(word);
            }
        } catch (IOException e) {
            System.out.println("Failed to process " + inputFile + ". More info: " + e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (List<String> stringList : mappedAnagrams.values()) {
                writer.write(String.join(" ", stringList));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to process " + outputFile + ". More info: " + e);
        }
    }
}
