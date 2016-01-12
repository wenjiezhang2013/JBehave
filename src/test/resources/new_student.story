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

Scenario: find existing student
Given a teacher named Jack
When he has students: 
|name|
|Larry|
|Moe|
|Curly|
Then I should be able to find Curly

Scenario: find a student from a teacher readed from remote service
Given a teacher named John found from remote service has not student
Then trying to find a student called Lily from his class should throw exception