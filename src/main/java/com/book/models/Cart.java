package com.book.models;

import javax.persistence.*;

@Entity
public class Cart
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartid;
	private String userid;
	private long bookid;
	private String title;
	private int price;
	public Cart() {}
	
	public Cart(String userid,long bookid, String title, int price) 
	{
		this.userid=userid;
		this.bookid = bookid;
		this.title = title;
		this.price = price;
	}

	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public long getBookid() {
		return bookid;
	}
	public void setBookid(long bookid) {
		this.bookid = bookid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
