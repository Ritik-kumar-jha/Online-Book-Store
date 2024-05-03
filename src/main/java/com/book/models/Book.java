package com.book.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Book
{
	@Id
	@GeneratedValue(generator = "book_seq")
	@SequenceGenerator(name = "book_seq",sequenceName = "BOOK_SEQ",initialValue = 11111)
	private long bid;
	private String title;
	private String author;
	private String category;
	private String publisher;
	private int copies;
	private int price;
	public Book()
	{
		System.out.println("Object of Book class is created...");
	}
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public String getTitle() {
		System.out.println("Getter method is called...");
		return title;
	}
	public void setTitle(String title) {
		System.out.println("Setter method is called...");
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
