Bugs found in PetClinic application:

1.

Title: Pet names are not separated in anyway while owner has more than one pet

	Reproduction steps:
		1. Go to PetClinic application.
		2. Open Find Owners page and click on 'Find Owner' button without any search phrase in searchbox
		3. Find any owner with more than one pet
	Actual result:
		Pet names are not separated i.e. "LeoDoe"
	Expected result:
		Pet names should be separated i.e. "Leo, Doe" 
		
	Additional information:
		Application version: x.x.x
		Environment: test
		

2.

Title: There is a SQL error message when trying to add new owner with very long field value

	Reproduction steps:
		1. Go to PetClinic application.
		2. Open Find Owners page and click on 'Add Owner' button
		3. Try to add user with at least one field filled with more than 30 characters (for example 31 characters)
	Actual result:
		There is a SQL error message on application. It throws an exception org.h2.jdbc.JdbcSQLDataException.
	Expected result:
		There should be additional validation to handle that case
		or
		There should be more characters allowed in each field
		
	Additional information:
		Application version: x.x.x
		Environment: test
		
		
3.

Title: "Update Owner" button changes to "Add Owner"

	Reproduction steps:
		1. Go to PetClinic application.
		2. Open Find Owners page and click on 'Find Owner' button without any search phrase in searchbox
		3. Click on any owner name
		4. Click on 'Edit Owner' button
		5. Make validation message appear (i.e. delete value of first name field and click 'Update Owner' button)
	Actual result:
		"Update Owner" button changes to "Add Owner"
	Expected result:
		Button text should not be changed
		
	Additional information:
		Application version: x.x.x
		Environment: test
		

4.

Title: Check marks should not be on fields before submitting add owner form for the first time

	Reproduction steps:
		1. Go to PetClinic application.
		2. Open Find Owners page and click on 'Add Owner' button
	Actual result:
		Check marks are on the empty fields signalizing that there is no validation errors
	Expected result:
		TBD
		
	Additional information:
		Application version: x.x.x
		Environment: test

=======================================

Replacement for Selenium

Cypress with a cypress-cucumber-preprocessor npm package (https://www.npmjs.com/package/cypress-cucumber-preprocessor). Due to lack of updates by side of Selenium creators, this technology slowly going to be deprecated. Cypress is a JS/TS technology with often updates.

=======================================

The way selectors are used in tests makes them very prone to change due to application changes. As we fully participate in development process we can add some HTML5 attributes for example

    <element name="example" attribute="exampleElement" />

and refer to them in code with

    webDriver.findElements(By.xpath("//element[@attribute='exampleElement']"))

As long as we will not be in need of changing those attributes, we would not be in need of changing selectors.
