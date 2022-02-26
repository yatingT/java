run: compile test
	 java --module-path JavaFXForLinux/ --add-modules javafx.controls PassageAnalyzer

compile: class

test: class
	java -jar junit5.jar -cp . --scan-classpath -n PessageTest

class: 
	javac RedBlackTree.java
	javac RBT.java
	javac passageUpload.java
	javac Passage.java
	javac PassageInterface.java
	javac --module-path JavaFXForLinux/ --add-modules javafx.controls PassageAnalyzer.java
	javac -cp .:junit5.jar PessageTest.java
	
clean:
	$(RM) *.class
	$(RM) *.java~
