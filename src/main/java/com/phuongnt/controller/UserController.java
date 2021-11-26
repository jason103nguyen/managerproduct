package com.phuongnt.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.phuongnt.dto.UserDto;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private List<UserDto> listUser = new ArrayList<>();
	private MessageSource messageSource;
	
	@Autowired
	public UserController(MessageSource messageSource) {
		
		listUser.add(new UserDto(1, "Nguyen Thi Ngoc Diem", 31));
		listUser.add(new UserDto(2, "Nguyen Thi Diem Huong", 28));
		listUser.add(new UserDto(3, "Nguyen Tri Phuong", 25));
		this.messageSource = messageSource;
	}
	
	@GetMapping(value = "/add-user")
	public String addUser(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		String imgUrl = "/img/raw_avatar.jpg";
		model.addAttribute("imgUrl", imgUrl);
		return "addUser";
	}
	
	@PostMapping(value = "/add-user")
	public String addUser(@ModelAttribute(name = "user") UserDto user, @RequestParam(name = "file") MultipartFile file) {
		
		String pathImg = "E:\\01_Java\\10_Spring\\03_Source_Code_JMaster\\managerproduct\\target\\classes\\static\\img\\" + file.getOriginalFilename();
	
		try {
			File newFile = new File(pathImg);
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		user.setId(listUser.size() + 1);
		user.setImg("/img/" + file.getOriginalFilename());
		
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
		model.addAttribute("listUser", listUser);
		return "showAllUser";
	}
}