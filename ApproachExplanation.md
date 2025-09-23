### Overview
This program reads words from a text file, groups anagrams together, and writes the results to an output file.

`Anagram` definition: Two words are anagrams if they contain the same letters in any order.
Example: "race" and "care".

The core idea is to create a unique key for each word so that all words with the same letters share the same key.

### Key Generation Approaches

a) Sorting Letters (commented out)

```java
char[] chars = word.toCharArray();
Arrays.sort(chars);
String key = new String(chars);

```
Example:
```json
"race" -> ['a','c','e','r'] -> "acer"
"care" -> ['a','c','e','r'] -> "acer"
```

- Pros: Simple to implement.
- Cons: Sorting has `O(m log m)` time complexity (`m` = word length).

b) Letter Frequency (Current Approach)
- Count occurrences of each letter `a-z` in an array of size 26.
- Convert the array to a string key.
- Words with the same letters produce the same key.

Example:
Word = `race`

| Letter | a | b | c | d | e | … | r |
| ------ | - | - | - | - | - | - | - |
| Count  | 1 | 0 | 1 | 0 | 1 | … | 1 |

Key generated:
```json
"1,0,1,0,1,...,1,"
```
- `race` and `care` → same key → grouped together.

Why faster:
- Counting letters is `O(m)`, faster than sorting `O(m log m)`.
- Converting a 26-element array to a string is effectively constant time.

### Maintainability
- Clear separation of tasks: reading → processing → writing.
- Comments explain key generation and logic.
- Can be refactored into methods for larger projects.
- Uses standard Java libraries → portable and easy to maintain.

### Scalability

- Handles millions of words efficiently.
- Uses `HashMap` for grouping: `O(1)` average insertion/lookup.
- Memory grows with unique anagram patterns, not total words.
- Efficient I/O with B`ufferedReader` and `BufferedWriter`.

### Performance
- Reading: `BufferedReader` → fast line-by-line reading.
- Processing: Frequency array key avoids sorting → faster for long words or large datasets.
- Writing: `BufferedWriter` → fast output.

### External Libraries
- None used.
- Uses only standard Java libraries: `java.io`, `java.util`.
- Reason: No extra dependencies needed, improves portability and simplicity.

### Diagram – Frequency Array Concept
```json
Word: "race"
Letters: r a c e

Frequency array (size 26):
Index:  0(a) 1(b) 2(c) 3(d) ... 4(e) ... 17(r) ... 25(z)
Values: 1     0     1     0   1       1        0

Key: "1,0,1,0,1,0,...,1,...,0,"
```
- Words with the same letters → same frequency array → same key → grouped together.

### Scaling to Extremely Large Datasets
Storing all words in memory is impossible for this scale. We need to adapt the approach:
 - External Sorting / Streaming
   - Process input in chunks that fit in memory.
   - For each chunk, we generate and write keys for words
   - after all chunks are done, merge the temp files and create the final output file with all anagrams.

### Summary
- Efficient, maintainable, and scalable approach.
- Frequency-array key method is faster than sorting for large datasets.
- No external libraries → simple build and run.
- Suitable for large input files with millions of words.

