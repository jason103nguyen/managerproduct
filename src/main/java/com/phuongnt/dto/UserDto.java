package com.phuongnt.dto;

import com.phuongnt.entities.User;

public class UserDto {

	private int id;
	private String name;
	private String img = "/img/raw_avatar.jpg";
	private int age;
	
	public UserDto() {
	}

	public UserDto(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public UserDto(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", img=" + img + ", age=" + age + "]";
	}
	
}
