Feature: Amazon Product Search
  As a user
  I want search product
  On amazon shopping website


  @RegressionTests @SmokeTests
  Scenario: Search for information technology book
    Given I go to "Amazon homepage"
    When I look for information technology books
    And I search for The Cucumber Book by Matt Wynne and Aslak Hellesoy
    Then I should the book title The Cucumber Book: Behaviour-Driven Development for Testers and Developers (Pragmatic Programmers)
    And author by by Matt Wynne  (Author), Aslak Hellesoy (Author)
    And breadcrumbs should show Books › Computers & Technology › Programming

