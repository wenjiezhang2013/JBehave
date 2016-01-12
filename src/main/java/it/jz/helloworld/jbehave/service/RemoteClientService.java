package it.jz.helloworld.jbehave.service;

import java.util.ArrayList;
import java.util.List;

import it.jz.helloworld.jbehave.model.Teacher;

public class RemoteClientService {

    public List<Object> getList() {
        return new ArrayList<>();
    }

    /**
     * This is just a stub, let's assume we need to read the data from either external service or DB
     * 
     * @param name
     * @return
     */
    public Teacher readTeacher(String name) {
        return new Teacher(name);
    }

}
