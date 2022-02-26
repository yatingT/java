run: compile
        java --module-path JavaFXForLinux/ --add-modules javafx.controls PassageAnalyzer
compile: code_run

test: code_run
        java -jar junit5.jar -cp . --scan-classpath -n PassageTest
        
code_run: 
        javac RedBlackTree.java
        javac RBT.java
        javac passageUpload.java
        javac Passage.java
        javac passageInterface.java
        javac --module-path JavaFXForLinux/ --add-modules javafx.controls PassageAnalyzer.java
        javac -cp .:junit5.jar PassageTest.java
        
clean:
        $(RM) *.class
        $(RM) *.java~
