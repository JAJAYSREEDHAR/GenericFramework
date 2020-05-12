Echo Starting Selenium Standalone Server

::java -jar selenium.jar commandName

SET path=%~dp0drivers
Echo %path%
::set SELENIUM_VERSION=4.0.0-alpha-5

::cd %path%



start java -jar %path%\selenium-server-4.0.0-alpha-5.jar standalone
::%JAVA_HOME%\bin\java -jar %path%selenium-server-4.0.0-alpha-5.jar standalone

:: http://localhost:4444/status

:: point your RemoveWebDriver to http://localhost:4444/ 





