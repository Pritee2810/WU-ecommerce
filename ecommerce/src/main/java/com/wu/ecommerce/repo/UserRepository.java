package com.wu.ecommerce.repo;

import java.util.Optional;

import com.wu.ecommerce.dto.User;

public interface UserRepository {
	public User addUser(User user);
	public User getEmployeeByUserId(String id) throws Exception;
	
	
	public Optional<User[]> getUsers();
	public Optional<User[]> getAllUsersByCategory(String cat);
	
	public String removeUserByUserId(String id);
	public User updateUserByUserId(String id, User user);
	
	default void display() {
		System.out.println("hello from interface");
	}
}
