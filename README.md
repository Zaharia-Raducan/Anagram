### How to Build and Run.
1. Prerequisites
    - make sure Java JDK is installed on your system (Java 8 or higher);
    - check by running in terminal/cmd:
   ```
      java -version
      javac -version
   ```
   You should see the Java version number.

2. File structure
   - your project should look like this:
   ```graphql
   Anagram/
   |-input/sample.txt # your input file with one word per line
   |-output/ # folder where output will be written
   |-src/io/dev/anagram/Main.java
   |-run.sh
   |-run.bat
   |-README.md
   ```
   
3. Compile the program or see 6 for easier approach.
    - Open the terminal/cmd in the project folder and run: 
   ```bash
    javac -d out src/io/dev/anagram/Main.java
   ```
   - This will compile your program and produce a Main.Class file in the same folder.
   - No Errors? You're ready to run!

4. Run the program
   ```bash
    java -cp out io.dev.anagram.Main
   ```
   - The program will:
   1. Read the words from `input/sample.txt`;
   2. Group all anagrams;
   3. Write the grouped words to `output/sample.txt`.
   - After running, check `output/sample.txt` to see the result.

5. Build and Run Using Scripts
    - Linux/MacOS (`run.sh`)
      - Make sure you have Java JDK installed (Java 8 or higher)
      - Open a terminal in your project folder.
      - Make the script executable (only needed once):
      ```bash 
      chmod +x run.sh
      ```
      - Run the script:
      ```bash
      ./run.sh
      ```
    
    - Windows (`run.bat`)
      - Make sure you have Java JDK installed (Java 8 or higher).
      - Open Command Prompt in your project folder.
      - Double-click `run.bat`, or run:
      ```bash
      run.bat 
      ```
      
6. Notes.
    - Input file: one word per line.
    - Output file: groups of anagrams on each line, words separated by spaces
    - Program ignores empty lines and converts words to lowercase.