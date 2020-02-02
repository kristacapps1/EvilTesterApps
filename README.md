# TestingApp
This repo is the set of projects created by https://github.com/eviltester to practice good testing techniques.
I have configured his repo to build and run in IntelliJ IDEA  
## Setup
1. Ensure IntelliJ IDEA is installed properly and is able to use Maven
2. Add GitHub account by doing File -> Settings -> Version Control -> GitHub and add account login
3. Select File -> New -> Project from Version Control...
4. Enter url https://github.com/kristacapps1/EvilTesterApps.git 
5. When IntelliJ asks if you want to create new project from source select No or else it will overwrite the project settings
6. Do File -> Open and select the folder EvilTesterApps/TestingApp-1.3.7 
7.The IntelliJ source roots and their module and lib dependencies will already be set up and should compile correctly. To compile, right click module named testingapp and select Build Module. The module dependencies will ensure all the other modules are built first.
## Running Tests
In the Project view, right click for example test [thepulpertests]/java/com.javafortesters.pulp/domain and select Run Tests in com.javafortesters.pulp.domain  
Tests can also be run with coverage information by selecting Run with coverage
## Resources
Original repo link: https://github.com/eviltester/TestingApp  
EvilTester app introduction: https://www.youtube.com/watch?time_continue=360&v=mpwD2-c0Rok&feature=emb_logo  
EvilTester site: https://www.eviltester.com/  
IntelliJ Docs: https://www.jetbrains.com/help/idea/  
JUnit Docs for v4.12: https://junit.org/junit4/javadoc/4.12/overview-summary.html  
Spark Java Docs: http://sparkjava.com/documentation  
