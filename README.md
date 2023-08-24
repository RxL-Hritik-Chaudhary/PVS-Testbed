

# PVS-testbed


Please read carefully before setup this repository.

## 1. Setup and Run project in local machine

a. Use SSH to clone this project. After Clone from root directory of project go to pvSignalTest directory. <br/>
b. Change chromedriver (acc to chrome browser version in your machine). <br/>
c. Create 'uploads' folder to your home of your system OS(Filemanager). <br/>
d. To change configurations(like db,app url etc) edit in uiconfiguration.properties and application.properties files.<br/>
e. Xlsx test-cases file name must be importFile.xlsx <br/>
f. Use command gradle bootRun inside pvSignalTest directory instead of root directory. <br/>

## 2.How to push code to this repo

a. All git commands must performed on root directory of project instead of pvSignalTest directory.


##NOTE: git commands will follow on root directory and all gradle/java commands works from pvSignalTest directory, Also for UI code it present in pvSignalTest/src/main/testbed-frontend which built using JS library react.


