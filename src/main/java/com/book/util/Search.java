package com.book.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import com.book.models.Book;
import com.book.services.BookService;

@Component
public class Search 
{
	@Autowired
	private BookService bservice;
	public void getBookListByBookid(Model model,long bid)
	{
		List<Book> list=bservice.getBookListById(bid);
		model.addAttribute("by","book id");
		model.addAttribute("list",list);
	}
	public void getBookListByAuthor(Model model,String author)
	{
		List<Book> list=bservice.getBookListByAuthor(author);
		model.addAttribute("author",author);
		model.addAttribute("by","Author");
		model.addAttribute("list",list);
	}
	public void getBookListByTitle(Model model,String title)
	{
		List<Book> list=bservice.getBookListByTitle(title);
		model.addAttribute("by","Title");
		model.addAttribute("list",list);
	}
	public void getBookListByCategory(Model model,String category)
	{
		List<Book> list=bservice.getBookListByCategory(category);
		model.addAttribute("by","Category");
		model.addAttribute("list",list);
	}
	public void getBookListByPublisher(Model model,String publisher)
	{
		List<Book> list=bservice.getBookListByPublisher(publisher);
		model.addAttribute("by","Publisher");
		model.addAttribute("list",list);
	}
}
