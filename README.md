# Cognizant QE Challenge
This project was done for the purpose of a QE Technical Challenge under Cognizant. The application under test is a front-end application found at https://todomvc.com/examples/react/dist/

For this project, I have used Java as the programming language of choice and jUnit as the testing framework with Selenium as the front end testing tool. The exact versioning of the dependencies can be found in this repository in the pom.xml file in the base directory.


# Test planning
For this technical challenge, I have determined that there exist a major overlap between functional testing and end-to-end testing as the application under test is small and limited. Most of the functionalities in the application are isolated and by considering functional testing, we can say that end-to-end flows are covered as well.

As this is a technical challenge, I have no visibility to the underlying system and requirements when it was designed, and I must approach this challenge in a blackbox approach and assume all the requirements in terms of features are implemented. We do this by first generating a test plan in accordance to manual exploratory testing before we convert this test plan into the basis for our test automation script. You can find this test plan attached in the repository as a xlsx file in the src/test folder.

# How to run
## Importing the project
To download/clone the project from Github to your local IDE for execution, you can follow this guide - https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository
## Running the testcases
To run the test cases, you would first need to have downloaded maven and Java and set the paths in your system variables.

For maven, you would need to create a MAVEN_HOME variable and a M2_HOME variable, with value being the path to your downloaded maven folder.

For Java, you would need to create a JAVA_HOME variable with value being the path to your downloaded Java folder.

It should look something like the below snapshot

![image](https://github.com/lewiesth/CognizantQEChallenge/assets/44538479/d95f5430-81f3-40d1-94b2-119bb1ebfc81)

After the setup is complete, open your terminal or powershell and change the directory to where you imported the project. Type the following command to execute the test cases. You must include a browser variable, being either chrome, firefox or msedge.

```
mvn test -Dbrowser=chrome
```
## Run with tags
The testcases within the project has also been tagged according to some categories, namely being Regression or Smoke testing test cases. For our purposes, regression test cases are executed when we intend to test the application comprehensively and smoke testing test cases are executed when we need to just test key functionalities of the system.

To execute test cases by tag, type the following command, where the D groups variable is the tags to run.
```
-mvn test -Dbrowser=chrome -D groups=Regression
```
## Run with allure
The native reporting functionality within jUnit is rudimentary and offers little in the way of visualising the test execution result. To augment report generation, I have included the Allure reporting framework (https://allurereport.org/).

To install Allure, I would recommend using your powershell terminal and install scoop as per their website - https://scoop.sh/

Then, after scoop is installed, run the following command in your powershell terminal

```
scoop install allure
```

To use the allure reports, run your test cases as per normal using maven, then we generate an allure report. You can do this using the below commands
```
mvn test -Dbrowser=chrome
allure generate
allure open
```

The allure report details would be stored in a seperate folder when allure generate is called, and the folder path is determined by the allure.properties file I have included in the test/resources folder

# Browser versioning
For simplicity's sake to run this project, I have also attached the webdrivers in this repository. However, in the event which there may be a versioning mismatch between your browsers and their respective webdrivers, you may be required to update the webdrivers or downgrade your browser versions such that they match each other.

For this project, I have used the following versions

| Application | Version |
| ----------- | -------- |
| Chrome browser | 122.0.6261.129 |
| Chromedriver | 122.0.6261.128 |
| Firefox browser | 123.0.1 |
| Geckodriver | 0.34.0 |
| Microsoft edge browser | 122.0.2365.80 |
| msedgedriver | 122.0.2365.80 |

# Overview
Overall, we have identified 8 testcases from our exploratory testing and have automated them. In addition, we have enabled cross-browser testing at the command line level to ensure that the application works across various browser platforms. Below, you will find snapshots of the results for each browser execution for all test cases. To view the report in full, after downloading the project, go to the allure-report folder found in the base directory and open the index.html file in any browser. You will then be able to navigate through the report.

### Chrome
![image](https://github.com/lewiesth/CognizantQEChallenge/assets/44538479/6a12298e-4ac5-4509-b414-98fbff2cf808)

### Firefox
![image](https://github.com/lewiesth/CognizantQEChallenge/assets/44538479/cdc62bfa-ad30-4f54-a66f-f56f8e510424)

### MSEdge
![image](https://github.com/lewiesth/CognizantQEChallenge/assets/44538479/d9ce6a03-7652-4508-a034-a5e1fa69c4a9)

