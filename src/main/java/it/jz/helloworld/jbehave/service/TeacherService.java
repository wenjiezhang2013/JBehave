package it.jz.helloworld.jbehave.service;

import it.jz.helloworld.jbehave.model.Teacher;

public class TeacherService {

    private RemoteClientService client;

    public void setClient(RemoteClientService client) {
        this.client = client;
    }

    public Teacher findTeacher(String teacherName) {
        return client.readTeacher(teacherName);
    }

}
