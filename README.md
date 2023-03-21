# Weather.com Test with Serenity and Cucumber using Screen Play Pattern
This is the repository of maven project to running the test for weather.com with the screenplay pattern of Serenity with Feature File of Cucumber


# Getting Start
1. [Maven](https://maven.apache.org/)
2. [Serenity Report](https://serenity-bdd.github.io/docs/reporting/the_serenity_reports)
3. [Serenity Cucumber Starter template](https://github.com/serenity-bdd/serenity-cucumber-starter)
4. [The Screenplay Pattern](https://www.browserstack.com/guide/screenplay-pattern-approach-in-selenium)

# Installation
The following softwares are required:
- [JDK 19 Installation](https://docs.oracle.com/en/java/javase/18/install/overview-jdk-installation.html)
- [Maven installation](https://maven.apache.org/install.html)

# Set up
1. Downloading maven dependencies
 
```
mvn clean install
```

# Running test cases
Using mvn site plugin to run test and generate surefire report
```
mvn verify
```
Running with specific browser (dault is chrome), using the webdriver.driver configuration of serenity (not implement capabilities for firefox yet)
```
// Run on firefox
mvn verify -Dwebdriver.driver=firefox 
```

# Report
- The test report will be generated at target/site/serenity folder, name "index.html"
![Screenshot 2023-03-21 at 7 24 03 am](https://user-images.githubusercontent.com/47560307/226493136-afd7a268-3610-432e-8fec-c94b8a0fbd57.png)
![Screenshot 2023-03-21 at 7 24 21 am](https://user-images.githubusercontent.com/47560307/226493152-7945f508-b0b7-4e61-945e-ffd8c62352d9.png)

- Report Content
![Screenshot 2023-03-21 at 7 25 16 am](https://user-images.githubusercontent.com/47560307/226493237-727d11e2-ed1e-4b62-9913-8f4ef422135f.png)
![Screenshot 2023-03-21 at 7 25 38 am](https://user-images.githubusercontent.com/47560307/226493280-90d72122-b35e-4e10-91e1-9989b10d8749.png)

- The weather information json file will be generated at "weather/", name "weather_info_{contry}_{browserName}_{date}.json"
![Screenshot 2023-03-21 at 7 29 16 am](https://user-images.githubusercontent.com/47560307/226493580-bc199d4a-5789-4544-b72f-0490de4d144d.png)

- Weather Information Json Schema:
```JSON
[{
  date: dayTitle,
  temperature: {
      day: dayTemperature,
      night: dayHumidity,
    },
  humidity: {
      day: nightTemperature,
      night: nightHumidity,
    }
 }
]
```

# Integrate with Jenkins
- Jenkins file pipeline was added to repository to integrate with Jenkins server
- Install Maven tools for Jenkins and using pipeline from SCM and enjoy CI Jenkins
![Screenshot 2023-03-21 at 7 30 08 am](https://user-images.githubusercontent.com/47560307/226493858-749533d6-63bc-4495-ba69-e4c10abb58ca.png)
- Jenkins jobs are running with headed mode on localhost to demo.
- After running automation test cases the surefire-report.html will be published to Jenkins job and the weather information json file will be added to artifacts
![Screenshot 2023-03-21 at 7 30 27 am](https://user-images.githubusercontent.com/47560307/226493894-fb6bcdc7-459f-4a69-9c58-1e13fa3db48f.png)
- Sample Weather HTML Report
![Screenshot 2023-03-21 at 7 31 09 am](https://user-images.githubusercontent.com/47560307/226493929-52fe7064-dfab-4681-a8f3-e98357454903.png)
![Screenshot 2023-03-21 at 7 31 20 am](https://user-images.githubusercontent.com/47560307/226493947-652e7b22-1c2c-47f7-851b-6543145b93b1.png)
- Sample Weather Information Artifacts
![Screenshot 2023-03-21 at 7 31 40 am](https://user-images.githubusercontent.com/47560307/226493975-f5d4e330-a1c0-47d9-884f-996570a2a79e.png)

## ENJOYING AUTOMATION WITH SERENITY BDD AND JENKINS!!!






