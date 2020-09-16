@echo off

set jar="DataAnalyzer.jar"
set output="bin\DataAnalyzer.jar"
if exist %jar% (
    echo Deleting previous DataAnalyzer.jar
    del %jar%
)
call compile
cd bin
echo Creating DataAnalyzer.jar
jar cfe DataAnalyzer.jar Main *.class
cd ..
if exist %output% (
    echo Moving DataAnalyzer.jar
    move %output% .
)
echo Build completed!
pause