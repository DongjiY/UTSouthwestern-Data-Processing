@echo off

call clean
echo Compiling source code...
javac -d bin -cp src src\Main.java
echo Successfully compiled source code