Feature: Application Login

Scenario: Login with valid credentials
Given Open any Browser
And Navigate to Login page
When User enters userName as "Seleniumtraining1234@gmail.com" and password as "selenium143" into the fileds
And User clicks on Login button
Then verify user is able to successfully login