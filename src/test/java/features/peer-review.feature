Feature:
  Peer Review

  Scenario: Validate Pull Request Invalid action event
    Given Get pull request
    When Pull Request Action is Invalid
    Then Peer Review Result status is FAIL

