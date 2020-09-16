@echo off

set classpath="bin\*.class"
if exist %classpath% (
    echo Deleting .class files in bin...
    del %classpath%
    echo Successfully deleted .class files in bin...
) else (
    echo Did not find any .class files in bin
)