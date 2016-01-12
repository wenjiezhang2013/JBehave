package it.jz.helloworld.jbehave.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.NoSuchElementException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.mockito.Mockito;

import it.jz.helloworld.jbehave.service.RemoteClientService;
import it.jz.helloworld.jbehave.service.TeacherService;

@UsingSteps
public class TeacherSteps {

    private Teacher teacher;
    private TeacherService teacherService;
    private RemoteClientService remoteClientService;

    @Given("a teacher named $teacherName")
    public void initTeacher(String teacherName) {
        teacher = new Teacher(teacherName);
    }

    @When("a new student $name joins his class")
    public void addStudents(String name) {
        teacher.students.add(new Student(name, teacher));
    }

    @Then("I should be able to find the student $name from his class")
    public void theTeacherHasThisStudent(String name) {
        assertNotNull(teacher.find(name));
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
    public void hasStudents(ExamplesTable studentsTable) {
        for (Map<String, String> row : studentsTable.getRows()) {
            String studentName = row.get("name");
            teacher.join(studentName);
        }
    }

    @Then("I should be able to find $studentName")
    public void findStudent(String studentName) {
        assertTrue(teacher.find(studentName) != null);
    }

    @Given("a teacher named $teacherName found from remote service has not student")
    public void findTeacher(String teacherName) {
        teacherService = new TeacherService();
        remoteClientService = Mockito.mock(RemoteClientService.class);
        teacherService.setClient(remoteClientService);
        Teacher remoteTeacher = new Teacher("remoteTeacher");
        // Stub the remote call response
        Mockito.when(remoteClientService.readTeacher(Mockito.anyString())).thenReturn(remoteTeacher);
        teacher = teacherService.findTeacher(teacherName);
    }

    @Then("trying to find a student called $studentName from his class should throw exception")
    public void findStudentFromRemoteTeacher(String studentName) {
        try {
            teacher.find(studentName);
        } catch (NoSuchElementException e) {
            assertTrue("Exception", true);
            return;
            
        }
        assertTrue("This line should not be executed.", false);
    }

}
