package com.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.models.User;
import com.book.repositories.UserRepository;

@Service
public class UserService 
{
	@Autowired
	private UserRepository repo;

	public void registerUser(User user) 
	{
		repo.save(user);	
	}
	public User getUser(String userid) 
	{
		return repo.findById(userid).orElse(null);
	}
	public void updateUser(User user) 
	{
		repo.save(user);
	}
	public void updatePassword(String pass, String uid) 
	{
		repo.updateUserPassword(pass,uid);
	}
}
