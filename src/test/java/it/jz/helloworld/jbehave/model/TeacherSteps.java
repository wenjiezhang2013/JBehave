package it.jz.helloworld.jbehave.model;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import static org.junit.Assert.*;

public class TeacherSteps {

	private Teacher teacher = new Teacher("Jack");

	@Given("a new student $name joins the class")
	public void addStudents(String name) {
		teacher.students.add(new Student(name, teacher));
	}

	@Then("I should be able to find the student $name")
	public void theTeacherHasThisStudent(String name) {
		assertNotNull(teacher.find(name));
	}

}
