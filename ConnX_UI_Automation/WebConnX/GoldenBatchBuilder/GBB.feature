Feature: Validate Fuctional Test on Golden Batch Builder Page

  Scenario: Add Golder Batch Template
    Given open browser and Go to URL
    And Provide vaild credentials
      | Field    | Value          |
      | Username | admin@connx.tk |
      | Password |         123456 |
    When Click on GBB tab
    Then Click on GB template
    And Insert/update record
      | Field          | Value       |
      | Name           | Malkiyat1   |
      | Bussiness Unit | Downstream  |
      | Product        | PR1         |
      | Process Stage  | Capture PR1 |
      | Total Duration |          40 |
      | Step Duration  | 30 min      |
    Then validate record message
    And logout ConnX
    Then close the browser

  Scenario: Delete Added Template
    Given open browser and Go to URL
    And Provide vaild credentials
      | Field    | Value          |
      | Username | admin@connx.tk |
      | Password |         123456 |
    When Click on GBB tab
    Then Search Parameter
      | Search Name |
      | Malkiyat    |
    And Delete the name from GBB Page
    And logout ConnX
    Then close the browser

  Scenario: Edit/update GBB Template
    Given open browser and Go to URL
    And Provide vaild credentials
      | Field    | Value          |
      | Username | admin@connx.tk |
      | Password |         123456 |
    When Click on GBB tab
    Then Search Name
    And Insert/update record
      | Field          | Value       |
      | Name           | Malkiyat1   |
      | Bussiness Unit | Downstream  |
      | Product        | PR1         |
      | Process Stage  | Capture PR1 |
      | Total Duration |          50 |
      | Step Duration  | 30 min      |
    And logout ConnX
    Then close the browser

