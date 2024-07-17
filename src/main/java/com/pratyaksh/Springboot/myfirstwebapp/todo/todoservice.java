package com.pratyaksh.Springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class todoservice {
	private static int todocount = 0;
	private static List<todo> todo = new ArrayList<>();
	static {
		todo.add(new todo(++todocount,"pratyaksh", "Learn AWS 1", LocalDate.now().plusYears(1), false));
		todo.add(new todo(++todocount,"pratyaksh", "Learn java 1", LocalDate.now().plusYears(2), false));
		todo.add(new todo(++todocount, "pratyaksh", "Learn python 1", LocalDate.now().plusYears(3), false));
	}

	public List<todo> findByUsername(String username) {
		Predicate<? super todo> predicate= 	todo->todo.getUsername().equalsIgnoreCase(username);
		return todo.stream().filter(predicate).toList();
	}

	public static void addtodo(String username, String description, LocalDate targetdate, boolean done) {
		todo Todo = new todo(++todocount, username, description, targetdate, done);
		todo.add(Todo);
	}

	public static void deletetodo(int id) {
		Predicate<? super todo> predicate= 	todo->todo.getId()==id;
		todo.removeIf(predicate);
	}

	public todo findById(int id) {
		Predicate<? super todo> predicate= 	todo->todo.getId()==id;
		todo Todo= todo.stream().filter(predicate).findFirst().get();
		return Todo;
	}

	public static void updatetodo(@Valid todo Todo	) {
	deletetodo(Todo.getId());
	todo.add(Todo);
	
		
	}

}
