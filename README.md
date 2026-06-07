# Standalone Selenium Web Automation Framework
Eclipse & JUnit/TestNG Import-Ready Maven Project Build

## Environment Setup Requirements
- Java Development Kit (JDK) 11 or newer
- Eclipse IDE for Enterprise Java and Web Developers (or Standard Eclipse with m2e plugin)

## Direct Steps to Import Project into Eclipse IDE
1. Open your designated **Eclipse Workspace**.
2. Navigate to the toolbar: Select **File ➔ Import...**
3. Choose **Maven ➔ Existing Maven Projects** and click **Next**.
4. Browse to choose the extracted folder containing this `pom.xml` configuration script blueprint.
5. Finish the workflow block context. Eclipse will resolve the dependencies and build automatically.

## Execution Rules
- Run via Maven Build Interface Terminal: `mvn clean test`
- Execution Report path layout mapping: `target/ExtentReports/SparkReport.html`

Base URL: https://www.saucedemo.com
