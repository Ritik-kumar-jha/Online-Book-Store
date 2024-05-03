package com.book.controllers.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.book.models.Book;
import com.book.services.BookService;
import com.book.util.Utility;

@Controller
@RequestMapping("purchase")
public class BuyBookController
{
	@Autowired BookService bservice;
	
	@RequestMapping("booklist")
	public String userHome(Model model,HttpSession ses,int pn,HttpServletRequest req) 
	{
		Utility.setUrl(ses, req);
		Page<Book> plist=bservice.getBookList(pn-1);
		List<Book> list=plist.toList();
		model.addAttribute("tp",plist.getTotalPages());
		model.addAttribute("pn",pn);
		ses.setAttribute("pn",pn);
		model.addAttribute("list",list);
		model.addAttribute("username",ses.getAttribute("name"));
		return "user/purchase/book-list";
	}
	@RequestMapping("buybook")
	public String getBuyBook(long bid,Model model) 
	{
		Book book=bservice.getBook(bid);
		model.addAttribute("book", book);
		return "user/purchase/buy-now";
	}
	@RequestMapping("buybooks")
	public String getBuyAllBook(Model model,HttpSession ses) 
	{
		model.addAttribute("blist",ses.getAttribute("blist"));
		return "/user/purchase/order-now";
	}
	@RequestMapping("bookdetails")
	public String getBookDetails(long bid,Model model) 
	{
		Book book=bservice.getBook(bid);
		model.addAttribute("book", book);
		return "user/purchase/book-details";
	}
	@RequestMapping("findall")
	public String findAll()
	{
		return "redirect:/purchase/booklist?pn=1";
	}
}
