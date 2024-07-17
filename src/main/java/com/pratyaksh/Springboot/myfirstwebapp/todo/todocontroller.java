package com.pratyaksh.Springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class todocontroller {
	public todocontroller(todoservice todoservice) {
		super();
		Todoservice = todoservice;
	}

	public todoservice Todoservice;

	@RequestMapping("list-todo")
	public String listalltodos(ModelMap model) {
		List<todo> todo = Todoservice.findByUsername((String)getusername(model) );
		model.addAttribute("todo", todo);
		return "listtodo";
	}

	private String getusername(ModelMap model) {
		 org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return authentication.getName();
	}

	@RequestMapping(value="addtodo", method = RequestMethod.GET)
	public String showtodo(ModelMap model) {
		todo todo=new todo(0,(String)getusername(model), "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}
	@RequestMapping(value="addtodo", method = RequestMethod.POST)
	public String addtodo(  ModelMap model, @Valid todo todo,BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		todoservice.addtodo((String)getusername(model), todo.getDescription(), todo.getTargetdate(), false);
		return "redirect:list-todo";
	}
	@RequestMapping(value="deletetodo")
	public String showtodo(@RequestParam int id) {
		todoservice.deletetodo(id);
		return "redirect:list-todo";
	}
	@RequestMapping(value="updatetodo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		todo todo = Todoservice.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}
	@RequestMapping(value="updatetodo", method = RequestMethod.POST)
	public String updatetodo(  ModelMap model, @Valid todo todo,BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		todoservice.updatetodo(todo);
		todo.setUsername((String)getusername(model));
		return "redirect:list-todo";
	}
}
