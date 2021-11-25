package com.phuongnt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phuongnt.dto.UserDto;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private List<UserDto> listUser = new ArrayList<>();
	private MessageSource messageSource;
	
	@Autowired
	public UserController(MessageSource messageSource) {
		
		listUser.add(new UserDto(1, "diem", null));
		listUser.add(new UserDto(2, "huong", null));
		listUser.add(new UserDto(3, "phuong", null));
		this.messageSource = messageSource;
	}
	
	@GetMapping(value = "/add-user")
	public String addUser(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "addUser";
	}
	
	@PostMapping(value = "/add-user")
	public String addUser(@ModelAttribute(name = "user") UserDto user) {
		user.setId(listUser.size() + 1);
		listUser.add(user);
		return "redirect:/user/add-user";
	}
	
	@GetMapping(value = "/show-user/{id}")
	public String getUser(@PathVariable(value = "id") int id, Model model) {
		
		UserDto user = listUser.get(id - 1);
		model.addAttribute("user", user);
		return "showUser";
	}
	
	@GetMapping(value = "/update-user/{id}")
	public String updateUser(@PathVariable(value = "id") int id, Model model) {
		
		UserDto user = listUser.get(id - 1);
		model.addAttribute("user", user);
		
		return "updateUser";
	}
	
	@PostMapping(value = "/update-user")
	public String updateUser(@ModelAttribute(name = "user") UserDto user) {
		listUser.add(user.getId() - 1, user);
		return "redirect:/user/show-user/" + String.valueOf(user.getId());
	}
	
	@GetMapping(value = "/delete-user/{id}")
	public String deleteUser(@PathVariable(name = "id") int id) {
		
		listUser.remove(id - 1);
		return "redirect:/user/show-all-user";
	}
	
	@GetMapping(value = "/show-all-user")
	public String showAllUser(Model model) {
		model.addAttribute("title", messageSource.getMessage("user.title", null, null));
		model.addAttribute("listUser", listUser);
		return "showAllUser";
	}
}