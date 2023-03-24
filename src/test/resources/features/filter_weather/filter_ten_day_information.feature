Feature: Ten Day Weather Page
  William - who can work on website and retrieve data

  Scenario Outline: : Retrieve ten days weather information and save to file
    Given William does navigate to weather site
    And William does navigate to ten days page
    When William does retrieve weather information for <numberOfDay> days and ignore today is <ignoreToday>
    Then William should check the file exist and is not empty

    # Add if statement test for 15 and 0
    # numberOfDay = 0 and ignoreToday = true should throw error
    Examples:
    |numberOfDay|ignoreToday|
    |10         |true         |
    |15         |true         |
    |0          |true         |
    |10         |false        |
    |15         |false        |
    |0          |false        |