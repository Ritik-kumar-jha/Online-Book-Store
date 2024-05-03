package com.book.controllers.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bookstore")
public class HomeController
{
	@RequestMapping("/")
	public String getHome()
	{
		return "/home/home";
	}
	@RequestMapping("adminlogin")
	public String getAdminLogin(String msg,Model model)
	{
		model.addAttribute("msg",msg);
		return "home/admin-login";
	}
	@RequestMapping("userlogin")
	public String getUserLogin(String msg,Model model)
	{
		model.addAttribute("msg",msg);
		return "home/user-login";
	}
}
