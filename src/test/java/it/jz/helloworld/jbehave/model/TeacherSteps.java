package it.jz.helloworld.jbehave.model;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.annotations.When;

import it.jz.helloworld.jbehave.service.RemoteClientService;

@UsingSteps
public class TeacherSteps {

    private Teacher teacher;

    @Given("a teacher named $teacherName")
    public void init(String teacherName) {
        teacher = new Teacher(teacherName);
        
        RemoteClientService client = mock(RemoteClientService.class);

        List<Object> list = new ArrayList<>();
        
        list.add(new Object());
        when(client.getList()).thenReturn(list);
        
        teacher.serviceClient = client;
    }

    @When("a new student $name joins his class")
    public void addStudents(String name) {
        teacher.students.add(new Student(name, teacher));
    }

    @Then("I should be able to find the student $name from his class")
    public void theTeacherHasThisStudent(String name) {
        assertNotNull(teacher.find(name));
        assertEquals(teacher.getListFromExternalService().size(), 1);
    }

    @Then("I should not be able to find the student $name from his class")
    public void theTeacherDoesNotHaveThisStudent(String name) {
        try {
            teacher.find(name);

        } catch (NoSuchElementException e) {
            assertTrue("Exception", true);
            return;
        }
        assertTrue("This line should not be executed.", false);
    }

}
