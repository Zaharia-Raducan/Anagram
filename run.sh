#!/bin/bash

# compile
javac -d out src/io/dev/anagram/Main.java
if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

# run
java -cp out io.dev.anagram.Main
