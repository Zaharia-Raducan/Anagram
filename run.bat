@echo off
REM compile
javac -d out src\io\dev\anagram\Main.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    exit /b 1
)

REM run
java -cp out io.dev.anagram.Main
pause
