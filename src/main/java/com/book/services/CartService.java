package com.book.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.models.Cart;
import com.book.repositories.CartRepository;

@Service
public class CartService
{
	@Autowired private CartRepository repo;
	public void addBook(Cart cart) 
	{
		repo.save(cart);
	}
	public List<Cart> getCartItems(String userid) 
	{
		return repo.findByUserid(userid);
	}
	public Cart getCartByBid(String userid,long bid) 
	{
		return repo.findByBookid(userid,bid);
	}
}
