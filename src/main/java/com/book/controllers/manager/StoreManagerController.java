package com.book.controllers.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.book.models.Book;
import com.book.models.BookCategory;
import com.book.models.BookPublisher;
import com.book.repositories.CategoryRepository;
import com.book.repositories.PublisherRepository;
import com.book.services.BookService;
import com.book.util.Utility;

@Controller
@RequestMapping("manager")
public class StoreManagerController 
{
	@Autowired private BookService service;
	@Autowired private CategoryRepository crepo;
	@Autowired private PublisherRepository prepo;
	
	@RequestMapping("home")
	public String adminHome() 
	{
		return "manager/home/home";
	}
	@RequestMapping("managebooks")
	public String manageBook()
	{
		return "manager/manage/manage-books";
	}
	@RequestMapping("logout")
	public String managerLogout(HttpSession session)
	{
		session.invalidate();
		return "redirect:/";
	}
	@RequestMapping("outofstock")
	public String outOfStock(Model model,HttpSession ses,HttpServletRequest req) 
	{
		Utility.setUrl(ses, req);
		List<Book> list=service.getOutOfStockList();
		model.addAttribute("list",list);
		return "manager/stock/outofstock";
	}
	@RequestMapping("findall")
	public String findAll(HttpSession ses)
	{
		ses.setAttribute("author", null);
		return "redirect:/manager/booklist?pn=1";
	}
	@RequestMapping("searchbooks")
	public String searchBooks(Model model) 
	{
		List<BookCategory> clist=crepo.findAll();
		model.addAttribute("clist",clist);
		List<BookPublisher> plist=prepo.findAll();
		model.addAttribute("plist",plist);
		return "manager/search/search-books";
	}
}
