Feature: Validate Fuctional Test on Golden Batch Builder Page

  Background: 
    Given open browser and Go to URL "http://connxdev.appsandbox.tk/"

  Scenario: Add Golder Batch Template
    And Provide vaild credentials
      | Field    | Value          |
      | Username | admin@connx.tk |
      | Password |         123456 |
    When Click on GBB tab
    Then Click on GB template
    And Insert/update record
      | Field          | Value       |
      | Name           | M95         |
      # Bussiness Unit options :Downstream , Upstream
      | Bussiness Unit | Downstream  |
      | Product        | PR1         |
      | Process Stage  | Capture PR1 |
      | Total Duration |          40 |
      # Step Duration Options :30 min , 1 hour , 1:30 hour , 2 hours
      | Step Duration  | 30 min      |
      # Enable :True & False
      | Enable         | True        |
    Then validate record message
    And logout ConnX
    Then close the browser

  Scenario: Search GBB Template
    And Provide vaild credentials
      | Field    | Value          |
      | Username | admin@connx.tk |
      | Password |         123456 |
    When Click on GBB tab
    Then Search/Delete "Search-Name-M95"
    And logout ConnX
    Then close the browser

  Scenario: Delete GBB Template
    And Provide vaild credentials
      | Field    | Value          |
      | Username | admin@connx.tk |
      | Password |         123456 |
    When Click on GBB tab
    Then Search/Delete "Delete-Name-M95"
    And logout ConnX
    Then close the browser
