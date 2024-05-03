package com.book.controllers.search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.book.models.Book;
import com.book.services.BookService;
import com.book.util.Search;
import com.book.util.Utility;

@Controller
@RequestMapping("searchby")
public class SearchByManagerController
{
	@Autowired private Search search;
	@Autowired private BookService bservice;
	@RequestMapping("author/manager")
	public String searchByAuthor(Model model,String author,HttpSession ses,HttpServletRequest req) 
	{
		Utility.setUrl(ses,req);
		search.getBookListByAuthor(model,author);
		return "manager/search/search-book-list";
	}
	@RequestMapping("title/manager")
	public String searchByTitle(Model model,String title,HttpServletRequest req,HttpSession ses) 
	{
		Utility.setUrl(ses,req);
		search.getBookListByTitle(model,title);
		return "manager/search/search-book-list";
	}
	@RequestMapping("category/manager")
	public String searchByCategory(Model model,String category,HttpServletRequest req,HttpSession ses) 
	{
		Utility.setUrl(ses,req);
		search.getBookListByCategory(model,category);
		return "manager/search/search-book-list";
	}
	@RequestMapping("publisher/manager")
	public String searchByPublisher(Model model,String publisher,HttpServletRequest req,HttpSession ses) 
	{
		Utility.setUrl(ses,req);
		search.getBookListByPublisher(model,publisher);
		return "manager/search/search-book-list";
	}
	
	@RequestMapping("book/edit")
	public String getEditForm(long bid,Model model,HttpSession ses,HttpServletRequest req) 
	{
		Book book=bservice.getBook(bid);
		model.addAttribute("book", book);
		return "manager/search/edit-book";
	}
	@RequestMapping("book/editsave")
	public String updateBook(Book book,HttpSession ses) 
	{
		bservice.updateBook(book);
		return "redirect:"+ses.getAttribute("url");
	}
	@RequestMapping("book/remove")
	public String removeBook(long bid,HttpSession ses)
	{
		bservice.removeBook(bid);
		return "redirect:"+ses.getAttribute("url");
	}
}
