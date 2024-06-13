mvn clean test -Dtest=CucumberTest -Denvironment=qa  -Dbrowser=chrome allure:serve


browser может быть "chrome" или "firefox"
environment пока только qa