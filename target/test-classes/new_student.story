Meta:
@author Jack Zhang

Scenario: When new student joins the class

Given a teacher named Jack
When a new student John joins his class
Then I should be able to find the student John from his class


Scenario: Should not find a student who has not joined

Given a teacher named Jack
When a new student John joins his class
Then I should not be able to find the student Mike from his class