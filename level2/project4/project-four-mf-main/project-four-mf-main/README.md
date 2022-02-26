Project Title: Passage Analyzer

Brief Project Description:
The Passage Analyzer Application will allow users to submit one or more text passages to be analyzed. The user will be able to get the word counts of a specific passage, check whether a passage contains a word or phrase, list out all the words used, and check which word most commonly starts a sentence. 
Four Chosen Requirements that this Project Fulfills:

Hash Table: This app would utilize the hash table to store Passage objects

Red Black Tree: The Passage object would contain a Red Black Tree field that contains each word and their occurance in the passage

Stream and Lambda expression: This app would use the stream and lambda expression to load the words from the passage into the RBT

Regular expressions: This app would use regular expressions to pick out words from the passage



Data Wranglers: Ryan Brumm  

Application Data: 
Collect text files that contain between 100 and 500 words that will be analyzed. Will create class to open and extract text from files and put into passage objects.

Data Format:
All files will be in txt formats.



Back End Developer: Linxiu Zeng Yating Tian 

Data Processing:
<brief description of the processing that your program will perform on this data>
Linxiu: 
A Passage class will be created to store each passage inserted by a data wrangler. A passageInterface class will be created to store every passage object and required methods  based on the front end requirements. Every method used in this class makes use of the specific Passage object instead of generic objects. A hashtable object will be created in the passageInterface class to store every passage object, and a redBlackTree object will also be created to store every word in each passage after categorizing. 

Yating Tian:
An article object class will be created based on the input passage variable. 
A interface class to store the article project and all the required methods such as add(), getPassage(), remove(), PrintList().  
The hash table object in this interface stores all the article objects. 
The red balck tree object stores the words as nodes, and counts the frequency in order to get the most common words and etc. 

Front End Interface:
Passage analyzePath(String path): Return a passage object that is created from the .txt file indicated by path, return null if no file is found, DO NOT print out any error message

Passage analyzePassage(String text): Return a passage object that is created from text.

int getWordCount(Passage text): Return the total word count in text

String getWordList(Passage text): Return a string that contains all the words along with their occurrence in the passage, example format: “cat: 4\n” + “dog: 10”

boolean contains(Passage text, String word): Check whether the word is present in text.

Linxiu: 
All methods needed by the front end will be Passage objects and will be listed in the passageInterface class. The Passage class will keep a setter and getter method for data. 

Yating Tian
A interface class to store the article project and all the required methods such as add(), getPassage(), remove(), PrintList().  
The getter and setter method will be created based on its instance field. 




Front End Developer: Bill Yan Jacob Wadzinske 

User Commands:
Through JavaFX,
Button Load: read the text file indicated by user’s provided path, and present it in the interface

Button Analyze: analyze the text in a textfield, provide the relevant statistics listed out in the introduction in another text field.

Button Clear: clear all the textfields

Error Messages:
When the path provided by the user is invalid, prompt the user to check for spelling and file type.



Test Engineer: Hechao Wang  

Test Descriptions:
Test whether the message can be added to a hashtable and red black tree. Test whether the message can be removed from hashtable. Test whether constructor work.



Additional Responsibilities and Notes:
<list by role, any additional responsibilities that are expected of team members to help balance the workload for this project… if you are concerned about your project being too simple or too involved, this is also a good place to suggest plans for expanding or contracting your main idea>


Schedule:

Due 11/24
Data Wranglers
Locate/Create text passages to be used for testing and demonstration
Back End Developers
An incomplete passageInterface class that only includes all the method names and return types. A completed Passage class that has all required information correctly defined. 
Front End Developers
Definition of all the classes and their description regarding implementation expectation
Test Engineers
Build all the classes and the description.


Due 12/01
Data Wranglers
Develop an upload method to upload text passages into Passage objects and be ready for the backend.
Back End Developers
A rough draft that includes all the required methods, but may need further revised. 
Front End Developers
<describe group deliverables that will be pushed to github by this role>
Test Engineers
Complete a rough draft includes all test methods, and it will be changed after Back End Developers finish.



Due 12/08
Data Wranglers
Assist teammates with testing and finishing other parts of the code.
Back End Developers
Completed correct code which can be directly used.
Front End Developers
<describe group deliverables that will be pushed to github by this role>
Test Engineers
Finish all the code and record video. 


Signatures:

	Front End Developer 1	Bill Yan	11/16/2020
	Data Wrangler 1		Ryan Brumm	11/17/2020
            Back End Developer 1           Linxiu Zeng      11/17/2020
            Test Engineer 1                      Hechao Wang  11/18/2020
            Back End Developer 2           Yating Tian       11/17/2020
	Front End Developer 2	Jacob Wadzinske 11/17/2020


End of Proposal
