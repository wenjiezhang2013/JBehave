package it.jz.helloworld.jbehave.model;

import java.util.HashSet;
import java.util.Set;

public class Teacher {

	public String name;
	public Set<Student> students = new HashSet<>();
	
	public Teacher (String name) {
		this.name = name;
	}

	public Student find(String name) {
		return students.stream().filter(item -> item.name.equals(name)).findFirst().get();
	}
}
