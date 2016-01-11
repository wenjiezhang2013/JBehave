package it.jz.helloworld.jbehave.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.mockito.Mockito;

import it.jz.helloworld.jbehave.service.RemoteClientService;

@UsingSteps
public class TeacherSteps {

    private Teacher teacher;
    
    private RemoteClientService client;

    @Given("a teacher named $teacherName")
    public void init(String teacherName) {
        teacher = new Teacher(teacherName);
        
        client = Mockito.mock(RemoteClientService.class);

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
    
    @When("he has students: $studentsTable")
    public void hasStudents(ExamplesTable studentsTable){
        for (Map<String,String> row : studentsTable.getRows()) {
            String studentName = row.get("name");
            teacher.join(studentName);
        }
    }
    
    @Then("I should be able to find $studentName")
    public void findStudent(String studentName){
        assertTrue(teacher.find(studentName) != null);
    }

}
