run: compile
	java --module-path JavaFXForLinux/ --add-modules javafx.controls PassageAnalyzer

test: PassageTest.class
	java -jar junit5.jar -cp . --scan-classpath -n PassageTest 

compile: PassageAnalyzer.class

PassageTest.class: PassageAnalyzer.class PassageTest.java
	javac -cp .:junit5.jar PassageTest.java

PassageAnalyzer.class: PassageAnalyzer.java PassageBE.class Passage.class
	javac -cp .:Mystery.jar  --module-path JavaFXForLinux/ --add-modules javafx.controls PassageAnalyzer.java

PassageBE.class: PassageBE.java PassageInterface.class
	javac PassageBE.java

PassageInterface.class: PassageInterface.java Passage.class
	javac PassageInterface.java

Passage.class: Passage.java RedBlackTree.class
	javac Passage.java

RedBlackTree.class: RedBlackTree.java
	javac RedBlackTree.java

clear:
	rm *.class
	rm *.java~
