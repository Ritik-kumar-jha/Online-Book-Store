package com.book.controllers.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.models.BookCategory;
import com.book.models.BookPublisher;
import com.book.models.CreditCard;
import com.book.repositories.CategoryRepository;
import com.book.repositories.CreditCardRepository;
import com.book.repositories.PublisherRepository;
import com.book.util.Utility;

@Controller
@RequestMapping("card")
public class CardController
{
	@Autowired private CreditCardRepository repo;
	
	@RequestMapping("details")
	public String userHome(Model model,HttpSession ses) 
	{
		List<CreditCard> list=repo.findAllByUserid((String)ses.getAttribute("userid"));
		model.addAttribute("list", list);
		return "user/card/card-details";
	}
}
