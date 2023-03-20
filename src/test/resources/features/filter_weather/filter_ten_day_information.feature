Feature: Ten Day Weather Page
  William - who can work on website and retrieve data

  Scenario: : Retrieve ten days weather information and save to file
    Given William does navigate to weather site
    And William does navigate to ten days page
    When William does retrieve weather information for 10 days
    Then William should check the file exist and is not empty