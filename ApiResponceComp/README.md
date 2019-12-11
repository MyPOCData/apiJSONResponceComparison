# Verify Response of two set of URLs are same or not
-----------------------------------------------------

#How to take clone of this project
1. -->git clone https://github.com/MyPOCData/apiJSONResponceComparison.git
2. -->git fetch origin apiJSONResponceComparison
3. -->git checkout apiJSONResponceComparison


#Installation required
1. -->Machine should have Java (I used 1.8 version)
2. -->Maven should be install properly
3. -->Java editor like eclipse or Intellij


#Project Description
1. -->Project is Maven based
2. -->This project compare response of two urls and on the basis of result we conform both urls are equal or not.
User have two files. Each file contain set of urls line by line. Pick urls from both files line by line and hit on server. Get the response in form of JSON. Compare both JSON. If both JSON are same, return urls are equal otherwise not equal.
3. -->Verifying two URLs is a same process, so I used Dataprovider.In this dataprovider I create 2D array in which first colume contain all URLs of file1 and second column contain all URLs of file2.
4. -->Using Extent Reporting to show complete status of the test execution.


#Assumptions : 
1. -->There may be huge data in files, so I assume data (URLs) are in Excel files
2. -->Assuming there may be some lines for which URLs may be not present. In this case I fail the test case
3. -->Some URLs may be invalid in any file, in this case response never match, so return not equal but test case should be passed
4. -->If number of lines are different in both files, execute test case till the end of any one file.

#Tools or Technologies (pom.xml)
1. -->Java
2. -->Maven
3. -->TestNG
4. -->POI
5. -->json
6. -->httpclient
7. -->aventstack


#How to Execute
1. -->Open terminal
2. -->Reach project location by using cd command (cd /Users/.../apiJSONResponceComparison/ApiResponceComp)
3. -->Then pass command "mvn test" and press enter


#Input
1. -->In Files folder there are 2 excel files which contain URLs:
	a. -->File1.xlsx
	b. -->File2.xlsx


#Output 
1. -->Report "extend.html" is generated on project level