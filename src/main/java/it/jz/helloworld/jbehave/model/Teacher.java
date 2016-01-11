package it.jz.helloworld.jbehave.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.jz.helloworld.jbehave.service.RemoteClientService;

public class Teacher {

    public String name;
    public Set<Student> students = new HashSet<>();
    
    public RemoteClientService serviceClient;

    public Teacher(String name) {
        this.name = name;
    }

    public Student find(String name) {
        return students.stream().filter(item -> item.name.equals(name)).findFirst().get();
    }
    
    public void join(String studentName) {
        students.add(new Student(studentName, this));
    }
    
    public List<Object> getListFromExternalService(){
        return serviceClient.getList();
    }
}
