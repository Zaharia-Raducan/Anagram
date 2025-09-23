package io.dev.anagram;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String inputFile = "input/sample.txt"; // input file path
        String outputFile = "output/sample.txt"; // output file path

        // HashMap to store groups of potential anagrams
        // Key = sorted letters of a word
        // Value = list of words that match this key (anagrams)
        Map<String, List<String>> mappedAnagrams = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) { // we read the file using BufferedReader
            String word;
            // The logic for this loop
            // - we assign the next line to 'word' and check for null in one step
            // - we stop automatically when the end of the file is reached (readLine() returns null)
            while ((word = reader.readLine()) != null) {
                // we normalize the word
                // using trim() to remove whitespaces
                // then we use toLowerCase(Locale.ROOT) to ensures consistent lowercase conversion across all locales
                word = word.trim().toLowerCase(Locale.ROOT);
                if (word.isEmpty()) continue; // skip empty lines

                // basically, how it works?
                // we make a special code for the word:
                // - count how many times each letter a-z shows up in the word.
                // - remember the counts in a list of 26 numbers.
                //    note: Words with the same letters will have the same numbers.
                //
                // turn the numbers into a string:
                // put all the numbers together with commas.
                //    - the string we get is the “key” to find all words that are anagrams.
                //    - now words with the same letters go in the same group.
                //
                // this should be faster than sorting because counting letters is quick and easy
                int[] freq = new int[26]; // the default english alphabet
                for (char c : word.toCharArray()) {
                    if (c >= 'a' && c <= 'z') {
                        freq[c - 'a']++; // increase the char frequency
                    }
                }

                // convert the frequency array into a string key
                StringBuilder keyBuilder = new StringBuilder();
                for (int f : freq) {
                    keyBuilder.append(f).append(','); // should be something like "1,0,1,0,1"
                }
                String key = keyBuilder.toString();

//                // convert word to a char array to create a key
//                char[] chars = word.toCharArray();
//                // cort characters to get a canonical form for anagram checking
//                Arrays.sort(chars);
//                String key = new String(chars);

                // add the original word to the list corresponding to its sorted key
                mappedAnagrams.computeIfAbsent(key, _ -> new ArrayList<>()).add(word);
            }
        } catch (IOException e) {
            System.out.println("Failed to process " + inputFile + ". More info: " + e);
        }

        // for testing purposes
//         for (List<String> stringList : mappedAnagrams.values()) {
//             if (!stringList.isEmpty()) {
//                System.out.println(String.join(" ", stringList));
//             }
//         }

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