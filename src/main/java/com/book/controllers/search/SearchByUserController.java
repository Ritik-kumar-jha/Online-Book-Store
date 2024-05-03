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

@Controller
@RequestMapping("searchby")
public class SearchByUserController
{
	@Autowired private BookService bservice;
	@Autowired private Search search;
	
	@RequestMapping("bookid/user")
	public String searchByBookid(Model model,long bid,HttpSession ses,HttpServletRequest req) 
	{
		search.getBookListByBookid(model,bid);
		setUrl(ses,req);
		return "user/search/search-book-list";
	}
	@RequestMapping("author/user")
	public String searchByAuthor(Model model,String author,HttpSession ses,HttpServletRequest req) 
	{
		search.getBookListByAuthor(model,author);
		setUrl(ses,req);
		return "user/search/search-book-list";
	}
	@RequestMapping("title/user")
	public String searchByTitler(Model model,String title,HttpServletRequest req,HttpSession ses) 
	{
		search.getBookListByTitle(model,title);
		setUrl(ses,req);
		return "user/search/search-book-list";
	}
	@RequestMapping("category/user")
	public String searchByCategory(Model model,String category,HttpServletRequest req,HttpSession ses) 
	{
		search.getBookListByCategory(model,category);
		setUrl(ses,req);
		return "user/search/search-book-list";
	}
	@RequestMapping("publisher/user")
	public String searchByPublisher(Model model,String publisher,HttpServletRequest req,HttpSession ses) 
	{
		search.getBookListByPublisher(model,publisher);
		setUrl(ses,req);
		return "user/search/search-book-list";
	}
	@RequestMapping("user/buybook")
	public String getBuyBook(long bid,Model model) 
	{
		Book book=bservice.getBook(bid);
		model.addAttribute("book", book);
		return "user/search/buy-book";
	}
	private void setUrl(HttpSession ses,HttpServletRequest req)
	{
		ses.setAttribute("url",req.getRequestURI()+"?"+req.getQueryString());
	}
}
